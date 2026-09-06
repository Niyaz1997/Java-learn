package service;

import com.threaddemo.model.ThreadResult;
import com.threaddemo.util.Logger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LiveLockService extends BaseThreadService {
    private final Lock lock1 = new ReentrantLock();
    private final Lock lock2 = new ReentrantLock();
    private Thread thread1;
    private Thread thread2;

    @Override
    public void demonstrate() {
        logger.info("Starting LiveLock demonstration");

        createLiveLockThreads();
        startThreads();
        observeLiveLock();
        stopDemonstration();
    }

    private void createLiveLockThreads() {
        thread1 = new Thread(this::executeLiveLockThread1, "LiveLock-Thread-1");
        thread2 = new Thread(this::executeLiveLockThread2, "LiveLock-Thread-2");
    }

    private void executeLiveLockThread1() {
        while (running) {
            try {
                if (lock1.tryLock(50, TimeUnit.MILLISECONDS)) {
                    logger.debug("Thread-1: Acquired lock1");
                    waitFor(100);

                    if (lock2.tryLock(50, TimeUnit.MILLISECONDS)) {
                        logger.debug("Thread-1: Acquired lock2 - SUCCESS!");
                        lock2.unlock();
                        lock1.unlock();
                        break;
                    } else {
                        logger.debug("Thread-1: Failed to acquire lock2, releasing lock1");
                        lock1.unlock();
                        continue;
                    }
                }
                waitFor(10);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    private void executeLiveLockThread2() {
        while (running) {
            try {
                if (lock2.tryLock(50, TimeUnit.MILLISECONDS)) {
                    logger.debug("Thread-2: Acquired lock2");
                    waitFor(100);

                    if (lock1.tryLock(50, TimeUnit.MILLISECONDS)) {
                        logger.debug("Thread-2: Acquired lock1 - SUCCESS!");
                        lock1.unlock();
                        lock2.unlock();
                        break;
                    } else {
                        logger.debug("Thread-2: Failed to acquire lock1, releasing lock2");
                        lock2.unlock();
                        continue;
                    }
                }
                waitFor(10);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    private void startThreads() {
        thread1.setDaemon(true);
        thread2.setDaemon(true);
        startThread(thread1);
        startThread(thread2);
    }

    private void observeLiveLock() {
        waitForCompletion(3000);

        logThreadState(thread1);
        logThreadState(thread2);

        logger.info("LiveLock demonstrated - threads are active but not progressing");
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
        logger.info("LiveLock demonstration completed");
    }

    private void waitFor(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}