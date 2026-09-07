package service;

import com.threaddemo.model.ThreadResult;
import com.threaddemo.util.Logger;
import service.BaseThreadService;

public class DeadLockService extends BaseThreadService {
    private final Object resource1 = new Object();
    private final Object resource2 = new Object();
    private Thread thread1;
    private Thread thread2;

    @Override
    public void demonstrate() {
        logger.info("Starting DeadLock demonstration");

        createDeadLockThreads();
        startThreads();
        observeDeadLock();
        stopDemonstration();
    }

    private void createDeadLockThreads() {
        thread1 = new Thread(this::executeThread1, "DeadLock-Thread-1");
        thread2 = new Thread(this::executeThread2, "DeadLock-Thread-2");
    }

    private void executeThread1() {
        while (running) {
            synchronized (resource1) {
                logger.info("Thread-1: Acquired resource1");
                waitFor(100);

                synchronized (resource2) {
                    logger.info("Thread-1: Acquired resource2");
                    performWork();
                }
            }
        }
    }

    private void executeThread2() {
        while (running) {
            synchronized (resource2) {
                logger.info("Thread-2: Acquired resource2");
                waitFor(100);

                synchronized (resource1) {
                    logger.info("Thread-2: Acquired resource1");
                    performWork();
                }
            }
        }
    }

    private void performWork() {
        logger.debug("Performing work...");
    }

    private void startThreads() {
        thread1.setDaemon(true);
        thread2.setDaemon(true);
        startThread(thread1);
        startThread(thread2);
    }

    private void observeDeadLock() {
        waitForCompletion(2000);

        logThreadState(thread1);
        logThreadState(thread2);

        logger.info("DeadLock demonstrated - threads are blocked");
    }

    private void logThreadState(Thread thread) {
        ThreadResult result = new ThreadResult(
                thread.getName(),
                thread.getState(),
                thread.isDaemon(),
                0,
                "Current state: " + thread.getState()
        );
        logger.info(result.toString());
    }

    private void stopDemonstration() {
        stopThreads();
        waitForCompletion(100);
        logger.info("DeadLock demonstration completed");
    }

    private void waitFor(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}