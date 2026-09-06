package com.threaddemo.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

    private boolean debugMode = false;

    public void info(String message) {
        print("INFO", message);
    }

    public void debug(String message) {
        if (debugMode) {
            print("DEBUG", message);
        }
    }

    public void error(String message) {
        print("ERROR", message);
    }

    public void error(String message, Throwable throwable) {
        print("ERROR", message + " - " + throwable.getMessage());
        throwable.printStackTrace();
    }

    private void print(String level, String message) {
        String timestamp = LocalDateTime.now().format(FORMATTER);
        System.out.println(String.format("[%s] [%s] %s", timestamp, level, message));
    }

    public void setDebugMode(boolean debugMode) {
        this.debugMode = debugMode;
    }
}