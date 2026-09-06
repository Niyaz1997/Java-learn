package com.threaddemo.util;

import com.threaddemo.exception.ThreadDemoException;  // ДОБАВИТЬ ИМПОРТ

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadUtils {

    public static ThreadFactory createNamedThreadFactory(String namePrefix) {
        AtomicInteger threadNumber = new AtomicInteger(1);
        return r -> {
            Thread t = new Thread(r);
            t.setName(namePrefix + "-" + threadNumber.getAndIncrement());
            t.setDaemon(true);
            return t;
        };
    }

    public static void safeSleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ThreadDemoException("Thread interrupted during sleep", e);
        }
    }

    public static void printThreadState(Thread thread) {
        if (thread == null) {
            throw new IllegalArgumentException("Thread cannot be null");
        }
        System.out.printf("[%s] State: %s, Daemon: %s%n",
                thread.getName(),
                thread.getState(),
                thread.isDaemon());
    }

    public static boolean isThreadAlive(Thread thread) {
        return thread != null && thread.isAlive();
    }

    public static void interruptSafely(Thread thread) {
        if (thread != null && thread.isAlive()) {
            thread.interrupt();
        }
    }
}