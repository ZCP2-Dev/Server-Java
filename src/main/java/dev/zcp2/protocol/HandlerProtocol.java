package dev.zcp2.protocol;

import com.google.gson.JsonObject;
import io.netty.channel.ChannelHandlerContext;
import org.jetbrains.annotations.NotNull;

public interface HandlerProtocol {
    void handle(@NotNull ChannelHandlerContext ctx, @NotNull JsonObject data);
}
