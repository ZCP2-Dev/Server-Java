package dev.zcp2.protocol.implementations;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import dev.zcp2.protocol.HandlerProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import org.jetbrains.annotations.NotNull;

public class Protocol500 implements HandlerProtocol {
    @Override
    public void handle(@NotNull ChannelHandlerContext ctx, @NotNull WebSocketFrame data) {
        if(data instanceof TextWebSocketFrame frame) {
            try {
                JsonObject json = JsonParser.parseString(frame.text()).getAsJsonObject();
                JsonObject ret = new JsonObject();
            } catch(JsonSyntaxException | IllegalStateException ignored) {
                JsonObject ret = new JsonObject();

            }
        }
    }

    @Override
    public void onActive(@NotNull ChannelHandlerContext ctx) {
    }

    @Override
    public void onInactive(@NotNull ChannelHandlerContext ctx) {
    }
}
