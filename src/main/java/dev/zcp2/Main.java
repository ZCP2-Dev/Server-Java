package dev.zcp2;

import dev.zcp2.config.WrappedConfig;
import dev.zcp2.network.WrappedWebSocketServer;
import dev.zcp2.process.WrappedConsole;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public final class Main {
    @Getter
    private static final WrappedConfig config = new WrappedConfig("config", true);
    @Getter
    private static File serverPath;
    @Getter
    private static final Logger logger = LoggerFactory.getLogger("ZephyrCraftPanel2");
    @Getter
    private static final WrappedWebSocketServer server = new WrappedWebSocketServer();
    @Getter
    @Setter
    private static WrappedConsole console = null;

    public static void main(String[] args) {
        config.load();
        logger.info("Configuration loaded.");
        serverPath = new File(Main.getConfig().getJson().get("ServerPath").getAsString());
        if(serverPath.listFiles() == null)
            //noinspection ResultOfMethodCallIgnored
            serverPath.mkdirs();
        logger.info("Server path at \"{}\".", serverPath);
        server.start(config.getJson().get("Port").getAsInt());
        logger.info("Websocket server started at \"localhost:{}\".", server.getPort());
    }
}
