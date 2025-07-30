package dev.zcp2.endpoint;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;

public class EndpointProcessingException extends Exception {
    @Getter
    private final int code;
    public EndpointProcessingException(@NotNull String message, int code) {
        super(message);
        this.code = code;
    }
}
