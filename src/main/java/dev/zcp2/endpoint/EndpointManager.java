package dev.zcp2.endpoint;

import com.google.gson.JsonObject;
import dev.zcp2.util.Global;

import java.util.HashMap;
import java.util.Map;

public class EndpointManager {
    public static final Map<String, Endpoint> endpoints = new HashMap<>();
    static {
        endpoints.put("StartServer", param -> {
            throw new EndpointProcessingException("Not implemented", 501);
        });

        endpoints.put("SoftStopServer", param -> {
            throw new EndpointProcessingException("Not implemented", 501);
        });

        endpoints.put("HardStopServer", param -> {
            throw new EndpointProcessingException("Not implemented", 501);
        });

        endpoints.put("RestartServer", param -> {
            throw new EndpointProcessingException("Not implemented", 501);
        });

        endpoints.put("RunCommand", param -> {
            throw new EndpointProcessingException("Not implemented", 501);
        });

        endpoints.put("GetHardwareUsage", param -> {
            throw new EndpointProcessingException("Not implemented", 501);
        });
        endpoints.put("Ping", param -> {
            throw new EndpointProcessingException("Not implemented", 501);
        });
        endpoints.put("Nslookup", param -> {
            throw new EndpointProcessingException("Not implemented", 501);
        });
        endpoints.put("Traceroute", param -> {
            throw new EndpointProcessingException("Not implemented", 501);
        });
        endpoints.put("IsPortIdle", param -> {
            throw new EndpointProcessingException("Not implemented", 501);
        });

        endpoints.put("GetOperatingSystemInfo", param -> {
            JsonObject ret = new JsonObject();
            switch (param.get("components").getAsString()) {
                case "system" -> ret.addProperty("system", Global.getSystemName());
                case "version" -> ret.addProperty("version", Global.getSystemVersion());
                case "architecture" -> ret.addProperty("architecture", Global.getSystemArchitecture());
                case "build" -> ret.addProperty("build", Global.getSystemBuild());
                case "processor" -> ret.addProperty("processor", Global.getProcessor());
                case "ram" -> ret.addProperty("ram", Global.getRam());
                case null, default -> {
                    ret.addProperty("system", Global.getSystemName());
                    ret.addProperty("version", Global.getSystemVersion());
                    ret.addProperty("architecture", Global.getSystemArchitecture());
                    ret.addProperty("build", Global.getSystemBuild());
                    ret.addProperty("processor", Global.getProcessor());
                    ret.addProperty("ram", Global.getRam());
                }
            }
            return ret;
        });
    }
}
