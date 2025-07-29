package dev.zcp2.socket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import lombok.Getter;
import lombok.SneakyThrows;

public class WrappedWebSocketServer {
    @Getter
    private int port;

    public WrappedWebSocketServer() {}

    @SneakyThrows
    public void start(int port) {
        this.port = port;
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) {
                        ChannelPipeline pipe = ch.pipeline();
                        pipe.addLast("HttpServerCodec", new HttpServerCodec());
                        pipe.addLast("HttpObjectAggregator", new HttpObjectAggregator(65536));
                        pipe.addLast("WebSocketServerProtocolHandler", new WebSocketServerProtocolHandler("/"));
                        pipe.addLast("WebSocketServerHandler", new WebSocketServerHandler());
                    }
                });
        ChannelFuture cf = bootstrap.bind(port).sync();
        cf.channel().closeFuture().addListener(future -> {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        });
    }
}
