package service;

import com.threaddemo.model.ThreadResult;
import com.threaddemo.util.Logger;

public class AlternatingPrinterService extends BaseThreadService {
    private boolean isOneTurn = true;
    private final Object lock = new Object();
    private Thread printerThread1;
    private Thread printerThread2;

    @Override
    public void demonstrate() {
        logger.info("Starting Alternating Printer demonstration");

        createPrinterThreads();
        startThreads();
        printAlternating();
        stopDemonstration();
    }

    private void createPrinterThreads() {
        printerThread1 = new Thread(this::printOne, "Printer-Thread-1");
        printerThread2 = new Thread(this::printTwo, "Printer-Thread-2");
    }

    private void printOne() {
        while (running) {
            synchronized (lock) {
                while (!isOneTurn && running) {
                    waitSafely();
                }
                if (!running) break;

                System.out.print("1 ");
                isOneTurn = false;
                lock.notifyAll();
            }
        }
        logger.debug("Printer-1 stopped");
    }

    private void printTwo() {
        while (running) {
            synchronized (lock) {
                while (isOneTurn && running) {
                    waitSafely();
                }
                if (!running) break;

                System.out.print("2 ");
                isOneTurn = true;
                lock.notifyAll();
            }
        }
        logger.debug("Printer-2 stopped");
    }

    private void waitSafely() {
        try {
            lock.wait(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void startThreads() {
        printerThread1.setDaemon(true);
        printerThread2.setDaemon(true);
        startThread(printerThread1);
        startThread(printerThread2);
    }

    private void printAlternating() {
        logger.info("Printing alternating numbers 1 and 2...");
        waitForCompletion(5000);
        System.out.println();
    }

    private void stopDemonstration() {
        stopThreads();
        synchronized (lock) {
            lock.notifyAll();
        }
        waitForCompletion(100);
        logThreadState(printerThread1);
        logThreadState(printerThread2);
        logger.info("Alternating Printer demonstration completed");
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

    // ИЗМЕНЕНО: protected вместо private
    @Override
    protected void waitForCompletion(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}