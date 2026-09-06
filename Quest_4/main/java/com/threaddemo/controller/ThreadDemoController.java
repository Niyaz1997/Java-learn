package controller;

import service.DeadLockService;
import service.LiveLockService;
import service.AlternatingPrinterService;
import com.threaddemo.util.Logger;

public class ThreadDemoController {
    private final Logger logger;
    private final DeadLockService deadLockService;
    private final LiveLockService liveLockService;
    private final AlternatingPrinterService printerService;

    public ThreadDemoController() {
        this.logger = new Logger();
        this.deadLockService = new DeadLockService();
        this.liveLockService = new LiveLockService();
        this.printerService = new AlternatingPrinterService();
    }

    public void runAllDemonstrations() {
        logger.info("\n");
        logger.info("  THREAD DEMONSTRATION APPLICATION");
        logger.info("\n");
        logger.info("");

        runDeadLockDemonstration();
        runLiveLockDemonstration();
        runAlternatingPrinterDemonstration();

        logger.info("");
        logger.info("\n");
        logger.info("  ALL DEMONSTRATIONS COMPLETED");
        logger.info("\n");
    }

    private void runDeadLockDemonstration() {
        logger.info(" Running DeadLock Demonstration ");
        deadLockService.demonstrate();
        logger.info(" DeadLock Demonstration Finished ");
        logger.info("");
    }

    private void runLiveLockDemonstration() {
        logger.info(" Running LiveLock Demonstration ");
        liveLockService.demonstrate();
        logger.info(" LiveLock Demonstration Finished ");
        logger.info("");
    }

    private void runAlternatingPrinterDemonstration() {
        logger.info(" Running Alternating Printer ");
        printerService.demonstrate();
        logger.info(" Alternating Printer Finished ");
        logger.info("");
    }
}