package dev.zcp2.socket;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.zcp2.util.ResponseUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.jetbrains.annotations.NotNull;

public class WebSocketServerHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    @Override
    protected void channelRead0(@NotNull ChannelHandlerContext ctx, @NotNull TextWebSocketFrame msg) {
        System.out.println("[debug-1]" + msg.text());

        JsonObject json = JsonParser.parseString(msg.text()).getAsJsonObject();
        JsonObject ret = new JsonObject();
        switch (json.get("Command").getAsString()) {
            case "start" -> {
            }
            case "stop" -> {
            }
            case "input" -> {
            }
            case "status" -> {
            }
            case "getSystemInfo" -> {
            }
            case "getServerInfo" -> {
                ret = ResponseUtil.getSystemResponse();
            }
            case null, default -> {}
        }
        ctx.channel().writeAndFlush(new TextWebSocketFrame(new Gson().toJson(ret)));
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println("[debug]active");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        System.out.println("[debug]inactive");
    }
}
