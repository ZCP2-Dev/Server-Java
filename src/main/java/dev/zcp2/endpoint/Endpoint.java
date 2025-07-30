package dev.zcp2.endpoint;

import com.google.gson.JsonObject;

import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface Endpoint {
    JsonObject call(@NotNull JsonObject param) throws EndpointProcessingException;
}
