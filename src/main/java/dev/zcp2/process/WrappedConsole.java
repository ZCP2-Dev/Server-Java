package dev.zcp2.process;

import lombok.Setter;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public class WrappedConsole {
    private Process process;
    private BufferedWriter processInput;
    private BufferedReader processOutput;
    private BufferedReader processError;
    private final ExecutorService executor = Executors.newFixedThreadPool(2);

    @Setter
    private Consumer<String> outputHandler = null;

    @Setter
    private Consumer<String> errorHandler = null;

    public WrappedConsole() {}

    @SneakyThrows
    public void start(@NotNull String command, @Nullable File directory) {
        ProcessBuilder pb = new ProcessBuilder();
        process = pb
                .command(command.split(" "))
                .directory(directory)
                .redirectError(ProcessBuilder.Redirect.PIPE)
                .redirectOutput(ProcessBuilder.Redirect.PIPE)
                .start();
        processInput = new BufferedWriter(new OutputStreamWriter(process.getOutputStream(), StandardCharsets.UTF_8));
        processOutput = new BufferedReader(new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8));
        processError = new BufferedReader(new InputStreamReader(process.getErrorStream(), StandardCharsets.UTF_8));
        executor.submit(this::readOutput);
        executor.submit(this::readError);
    }

    @SneakyThrows
    public void sendCommand(@NotNull String command) {
        if (processInput != null) {
            processInput.write(command);
            processInput.newLine();
            processInput.flush();
        }
    }

    @SneakyThrows
    public void close() {
        if (processInput != null) processInput.close();
        if (processOutput != null) processOutput.close();
        if (processError != null) processError.close();
        if (process != null) process.destroy();
        executor.shutdown();
    }

    public boolean isClosed() {
        return !process.isAlive();
    }

    @SneakyThrows
    private void readOutput() {
        String line;
        while ((line = processOutput.readLine()) != null)
            if(outputHandler != null)
                outputHandler.accept(line);
    }

    @SneakyThrows
    private void readError() {
        String line;
        while ((line = processError.readLine()) != null)
            if(errorHandler != null)
                outputHandler.accept(line);
    }
}
