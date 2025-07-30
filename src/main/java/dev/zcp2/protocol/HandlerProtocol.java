package dev.zcp2.protocol;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import org.jetbrains.annotations.NotNull;

public interface HandlerProtocol {
    void handle(@NotNull ChannelHandlerContext ctx, @NotNull WebSocketFrame data);
    void onActive(@NotNull ChannelHandlerContext ctx);
    void onInactive(@NotNull ChannelHandlerContext ctx);
}
