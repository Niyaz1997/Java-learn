package com.threaddemo.util;

public class Logger {
    private static boolean debugMode = false;

    public void info(String message) {
        System.out.println("[INFO] " + message);
    }

    public void debug(String message) {
        if (debugMode) {
            System.out.println("[DEBUG] " + message);
        }
    }

    public void error(String message) {
        System.err.println("[ERROR] " + message);
    }

    public void error(String message, Throwable throwable) {
        System.err.println("[ERROR] " + message);
        throwable.printStackTrace();
    }

    public void setDebugMode(boolean debugMode) {
        Logger.debugMode = debugMode;
    }

    public boolean isDebugMode() {
        return debugMode;
    }
}