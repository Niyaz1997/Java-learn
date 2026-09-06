package com.threaddemo.config;

public class ApplicationConfig {
    public static final int DEADLOCK_DEMO_DURATION = 2000;
    public static final int LIVELOCK_DEMO_DURATION = 3000;
    public static final int PRINTER_DEMO_DURATION = 5000;
    public static final int THREAD_WAIT_TIMEOUT = 100;
    public static final int LOCK_ATTEMPT_TIMEOUT = 50;

    public static final String DEFAULT_THREAD_PREFIX = "Thread-";
    public static final String DEADLOCK_THREAD_PREFIX = "DeadLock-";
    public static final String LIVELOCK_THREAD_PREFIX = "LiveLock-";
    public static final String PRINTER_THREAD_PREFIX = "Printer-";
}