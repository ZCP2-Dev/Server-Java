package dev.zcp2.util;

import com.google.gson.JsonObject;
import org.jetbrains.annotations.NotNull;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;

public final class SystemJsonUtil {
    public static @NotNull JsonObject getCpuUsage() {
        JsonObject ret = new JsonObject();
        SystemInfo systemInfo = new SystemInfo();
        CentralProcessor processor = systemInfo.getHardware().getProcessor();
        ret.addProperty("cpuUsage", processor.getSystemCpuLoad(1000) * 100);
        return ret;
    }
    public static @NotNull JsonObject getMemoryUsage() {
        JsonObject ret = new JsonObject();
        SystemInfo systemInfo = new SystemInfo();
        GlobalMemory memory = systemInfo.getHardware().getMemory();
        ret.addProperty("memoryUsage", (double)(memory.getTotal() - memory.getAvailable()) / memory.getTotal());
        return ret;
    }
    public static @NotNull JsonObject getMemoryTotal() {
        JsonObject ret = new JsonObject();
        SystemInfo systemInfo = new SystemInfo();
        GlobalMemory memory = systemInfo.getHardware().getMemory();
        ret.addProperty("memoryTotal", memory.getTotal() / 1024.0 / 1024.0);
        return ret;
    }
    public static @NotNull JsonObject getMemoryUsed() {
        JsonObject ret = new JsonObject();
        SystemInfo systemInfo = new SystemInfo();
        GlobalMemory memory = systemInfo.getHardware().getMemory();
        ret.addProperty("memoryTotal", (memory.getTotal() - memory.getAvailable()) / 1024.0 / 1024.0);
        return ret;
    }
    public static @NotNull JsonObject getDiskUsage() {
        JsonObject ret = new JsonObject();
        SystemInfo systemInfo = new SystemInfo();
        
        ret.addProperty("diskUsage", );
        return ret;
    }
}
