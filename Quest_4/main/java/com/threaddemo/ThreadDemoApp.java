package threaddemo;

import controller.ThreadDemoController;
import com.threaddemo.util.Logger;

public class ThreadDemoApp {

    public static void main(String[] args) {
        Logger logger = new Logger();

        try {
            validateArguments(args);

            ThreadDemoController controller = new ThreadDemoController();
            controller.runAllDemonstrations();

        } catch (Exception e) {
            logger.error("Application failed", e);
            System.exit(1);
        }
    }

    private static void validateArguments(String[] args) {
        if (args == null) {
            throw new IllegalArgumentException("Arguments cannot be null");
        }

        for (String arg : args) {
            if ("-- debug".equals(arg)) {
                Logger logger = new Logger();
                logger.setDebugMode(true);
                logger.debug("Debug mode enabled");
            }
        }
    }
}