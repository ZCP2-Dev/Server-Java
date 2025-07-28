package dev.zcp2.util;

import com.google.gson.JsonObject;
import org.jetbrains.annotations.NotNull;

public final class ResponseUtil {
    public static @NotNull JsonObject getSystemResponse() {
        JsonObject ret = new JsonObject();
        JsonObject sys = new JsonObject();
        sys.addProperty("CPUUsage", Global.getCpuUsage());
        sys.addProperty("MemoryUsage", Global.getMemoryUsage());
        sys.addProperty("MemoryTotal", Global.getMemoryTotal());
        sys.addProperty("MemoryUsed", Global.getMemoryUsed());
        sys.addProperty("DiskUsage", Global.getDiskUsage());
        sys.addProperty("DiskTotal", Global.gedDiskTotal());
        sys.addProperty("DiskUsed", Global.getDiskUsed());
        sys.addProperty("Uptime", Global.getUptime());
        ret.add("SystemInfo", sys);
        return ret;
    }
    public static @NotNull JsonObject getServerResponse() {
        JsonObject ret = new JsonObject();
        JsonObject server = new JsonObject();
        server.addProperty("Version", Global.getServerVersion());
        server.addProperty("LoaderVersion", Global.getLoaderVersion());
        server.addProperty("StartTime", Global.getStartTime());
        server.addProperty("PlayerCount", Global.getPlayerCount());
        server.addProperty("MaxPlayers", Global.getMaxPlayers());
        server.addProperty("Uptime", Global.getUptime());
        ret.add("ServerInfo", server);
        return ret;
    }
}
