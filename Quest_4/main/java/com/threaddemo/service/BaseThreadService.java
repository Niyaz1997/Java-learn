package service;

import com.threaddemo.util.Logger;
import com.threaddemo.util.ThreadUtils;

public abstract class BaseThreadService {
    protected final Logger logger;
    protected volatile boolean running = true;

    public BaseThreadService() {
        this.logger = new Logger();
    }

    public abstract void demonstrate();

    protected void startThread(Thread thread) {
        thread.start();
        logger.info("Thread started: " + thread.getName());
    }

    protected void stopThreads() {
        running = false;
        logger.info("Stopping all threads...");
    }

    protected void waitForCompletion(long millis) {
        ThreadUtils.safeSleep(millis);
    }
}