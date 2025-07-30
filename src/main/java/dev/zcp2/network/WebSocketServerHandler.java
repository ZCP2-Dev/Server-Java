package dev.zcp2.network;

import dev.zcp2.protocol.HandlerProtocol;
import dev.zcp2.protocol.ProtocolVersion;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import org.jetbrains.annotations.NotNull;

public class WebSocketServerHandler extends SimpleChannelInboundHandler<WebSocketFrame> {
    private final HandlerProtocol handler;

    public WebSocketServerHandler(@NotNull ProtocolVersion version) {
        handler = version.getProtocol();
    }

    @Override
    protected void channelRead0(@NotNull ChannelHandlerContext ctx, @NotNull WebSocketFrame frame) {
        handler.handle(ctx, frame);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        handler.onActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        handler.onInactive(ctx);
    }
}