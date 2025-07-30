package dev.zcp2.protocol;

import dev.zcp2.protocol.implementations.Protocol500;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

public enum ProtocolVersion {
    VERSION_500(new Protocol500());

    @Getter
    private final HandlerProtocol protocol;

    ProtocolVersion(@NotNull HandlerProtocol protocol) {
        this.protocol = protocol;
    }
}
