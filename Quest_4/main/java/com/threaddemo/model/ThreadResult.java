package com.threaddemo.model;

import java.time.LocalDateTime;

public class ThreadResult {
    private final String threadName;
    private final Thread.State state;
    private final boolean isDaemon;
    private final long executionTime;
    private final String message;

    public ThreadResult(String threadName, Thread.State state, boolean isDaemon,
                        long executionTime, String message) {
        this.threadName = threadName;
        this.state = state;
        this.isDaemon = isDaemon;
        this.executionTime = executionTime;
        this.message = message;
    }

    public String getThreadName() { return threadName; }
    public Thread.State getState() { return state; }
    public boolean isDaemon() { return isDaemon; }
    public long getExecutionTime() { return executionTime; }
    public String getMessage() { return message; }

    @Override
    public String toString() {
        return String.format("Thread: %s, State: %s, Daemon: %s, Time: %dms, Message: %s",
                threadName, state, isDaemon, executionTime, message);
    }
}