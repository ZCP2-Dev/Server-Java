package dev.zcp2.util;

import dev.zcp2.Main;
import org.jetbrains.annotations.NotNull;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;

public final class Global {
    public static double getCpuUsage() {
        SystemInfo systemInfo = new SystemInfo();
        CentralProcessor processor = systemInfo.getHardware().getProcessor();
        return processor.getSystemCpuLoad(1000) * 100;
    }
    public static double getMemoryUsage() {
        SystemInfo systemInfo = new SystemInfo();
        GlobalMemory memory = systemInfo.getHardware().getMemory();
        return (double)(memory.getTotal() - memory.getAvailable()) / memory.getTotal();
    }
    public static long getMemoryTotal() {
        SystemInfo systemInfo = new SystemInfo();
        GlobalMemory memory = systemInfo.getHardware().getMemory();
        return memory.getTotal() / 1024 / 1024;
    }
    public static long getMemoryUsed() {
        SystemInfo systemInfo = new SystemInfo();
        GlobalMemory memory = systemInfo.getHardware().getMemory();
        return (memory.getTotal() - memory.getAvailable()) / 1024 / 1024;
    }
    public static double getDiskUsage() {
        return (double) getDiskUsed() / gedDiskTotal();
    }
    public static long gedDiskTotal() {
        return Main.getServerPath().getTotalSpace() / 1024 / 1024;
    }
    public static long getDiskUsed() {
        return (Main.getServerPath().getTotalSpace() - Main.getServerPath().getUsableSpace()) / 1024 / 1024;
    }
    public static long getUptime() {
        SystemInfo systemInfo = new SystemInfo();
        return systemInfo.getOperatingSystem().getSystemUptime();
    }
    public static @NotNull String getServerVersion() {
        return Main.getConfig().getJson().get("Version").getAsString();
    }
    public static @NotNull String getLoaderVersion() {
        return Main.getConfig().getJson().get("ServerCore").getAsString();
    }
    public static @NotNull String getStartTime() {
        // TODO
        return "null";
    }
    public static int getPlayerCount() {
        // TODO
        return -1;
    }
    public static int getMaxPlayers() {
        // TODO
        return -1;
    }
}
