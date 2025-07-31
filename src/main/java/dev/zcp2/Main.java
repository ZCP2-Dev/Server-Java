package dev.zcp2;

import dev.zcp2.config.WrappedConfig;
import dev.zcp2.process.WrappedConsole;
import lombok.Getter;

import java.io.File;

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

        String tempStartArgs = "java -jar \"" + serverPath.getAbsolutePath() + "\\paper-1.20.1-196.jar\" -nogui";
        WrappedConsole console = new WrappedConsole();
        console.start(tempStartArgs, serverPath);
        console.setOutputHandler(System.out::println);
        console.setErrorHandler(System.err::println);

    }
}
