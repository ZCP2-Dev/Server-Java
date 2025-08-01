package dev.zcp2.network;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import dev.zcp2.Main;
import dev.zcp2.endpoint.EndpointManager;
import dev.zcp2.endpoint.EndpointProcessingException;
import dev.zcp2.util.Global;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import org.jetbrains.annotations.NotNull;

import java.net.InetSocketAddress;

public class WebSocketServerHandler extends SimpleChannelInboundHandler<WebSocketFrame> {

    public WebSocketServerHandler() {}

    @Override
    protected void channelRead0(@NotNull ChannelHandlerContext ctx, @NotNull WebSocketFrame data) {
        if(data instanceof TextWebSocketFrame msg) {
            try {
                JsonObject json = JsonParser.parseString(msg.text()).getAsJsonObject();
                int retGateway = json.get("gateway").getAsInt();
                JsonObject retBody = new JsonObject();
                switch (json.get("gateway").getAsInt()) {
                    case 0 -> {
                        // ping
                        retBody.addProperty("ServerInfo", Main.getConsole() == null || Main.getConsole().isClosed() ? 0 : 2);
                        retBody.addProperty("CpuUsage", (int) Global.getCpuUsage());
                        retBody.addProperty("MemoryUsage", (int) Global.getMemoryUsage());
                    }
                    case 1 -> {
                        // call api
                        JsonObject body = json.get("body").getAsJsonObject();
                        String method = body.get("endpoint").getAsString();
                        int id = body.get("id").getAsInt();
                        if(EndpointManager.endpoints.containsKey(method)) {
                            try {
                                JsonObject endpointJson = EndpointManager.endpoints.get(method).call(body.get("parameter").getAsJsonObject());
                                if(endpointJson!=null) retBody.add("info", endpointJson);
                                retBody.addProperty("id", id);
                                retBody.addProperty("status", 200);
                            } catch (EndpointProcessingException e) {
                                retBody.addProperty("id", id);
                                retBody.addProperty("status", e.getCode());
                                retBody.addProperty("errinfo", e.getMessage());
                            }
                        } else {
                            retBody.addProperty("id", id);
                            retBody.addProperty("status", 300);
                            retBody.addProperty("errinfo", "Endpoint not found");
                        }
                    }
                }
                JsonObject ret = new JsonObject();
                ret.addProperty("gateway", retGateway);
                ret.add("body", retBody);
                ctx.writeAndFlush(new TextWebSocketFrame(new Gson().toJson(ret)));
            } catch(JsonSyntaxException | IllegalStateException ignored) {
                JsonObject ret = new JsonObject();
                ret.addProperty("id", 0);
                ret.addProperty("status", 499);
                ret.addProperty("errinfo", "Invalid JSON");
                ctx.writeAndFlush(new TextWebSocketFrame(new Gson().toJson(ret)));
            } catch (Exception e) {
                JsonObject ret = new JsonObject();
                ret.addProperty("id", 0);
                ret.addProperty("status", 499);
                ret.addProperty("errinfo", e.getMessage());
                ctx.writeAndFlush(new TextWebSocketFrame(new Gson().toJson(ret)));
            }
        }
    }

    @Override
    public void channelActive(@NotNull ChannelHandlerContext ctx) {
        Main.getLogger().info("A channel connected from \"{}\".", ((InetSocketAddress) ctx.channel().remoteAddress()).getAddress().getHostAddress());
    }

    @Override
    public void channelInactive(@NotNull ChannelHandlerContext ctx) {
        Main.getLogger().info("A channel disconnected from \"{}\".", ((InetSocketAddress) ctx.channel().remoteAddress()).getAddress().getHostAddress());
    }
}