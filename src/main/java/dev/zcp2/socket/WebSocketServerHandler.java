package dev.zcp2.socket;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.zcp2.util.ResponseUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.PingWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PongWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import org.jetbrains.annotations.NotNull;

public class WebSocketServerHandler extends SimpleChannelInboundHandler<WebSocketFrame> {
    @Override
    protected void channelRead0(@NotNull ChannelHandlerContext ctx, @NotNull WebSocketFrame frame) {
        System.out.println("testawa:" + frame);
        if(frame instanceof PingWebSocketFrame msg) {
            System.out.println("test111");
        } else if(frame instanceof PongWebSocketFrame msg) {
            System.out.println("test222");
        } else if(frame instanceof TextWebSocketFrame msg) {
            try {
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
                        ret = ResponseUtil.getSystemResponse();
                    }
                    case "getServerInfo" -> {
                        ret = ResponseUtil.getServerResponse();
                    }
                    case null, default -> {
                    }
                }
                ctx.channel().writeAndFlush(new TextWebSocketFrame(new Gson().toJson(ret)));
            } catch (IllegalStateException e) {
                System.out.println("warn: non-json message: " + msg.text());
            }
        }
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
