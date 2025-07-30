package dev.zcp2;

import dev.zcp2.config.WrappedConfig;
import dev.zcp2.network.WrappedWebSocketServer;
import lombok.Getter;

import java.io.File;
import java.util.Objects;

public final class Main {
    @Getter
    private static final WrappedConfig config = new WrappedConfig("config", true);
    @Getter
    private static File serverPath;

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void main(String[] args) {
        config.load();
        serverPath = new File(Main.getConfig().getJson().get("ServerPath").getAsString());
        if(serverPath.listFiles() == null)
            serverPath.mkdirs();
        if(Objects.requireNonNull(serverPath.listFiles()).length == 0) {
            // TODO:初始化(?)
            WrappedWebSocketServer server = new WrappedWebSocketServer();
            server.start(10086);
            System.out.println("started");
        }
    }
}
