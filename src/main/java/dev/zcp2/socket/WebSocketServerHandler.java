package dev.zcp2.socket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import org.jetbrains.annotations.NotNull;

public class WebSocketServerHandler extends SimpleChannelInboundHandler<WebSocketFrame> {
    @Override
    protected void channelRead0(@NotNull ChannelHandlerContext ctx, @NotNull WebSocketFrame frame) {
        System.out.println("[debug]frame:" + ((TextWebSocketFrame)frame).text());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println("[debug]active-a");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        System.out.println("[debug]inactive-a");
    }
}
