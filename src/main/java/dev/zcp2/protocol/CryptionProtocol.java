package dev.zcp2.protocol;

import com.google.gson.JsonObject;
import org.jetbrains.annotations.NotNull;

public interface CryptionProtocol {
    @NotNull JsonObject encrypt(@NotNull JsonObject data);
    @NotNull JsonObject decrypt(@NotNull JsonObject data);
}
