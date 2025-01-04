package smarthome.reports;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggerManager {

    public static final Logger consumptionLogger = Logger.getLogger("ConsumptionLogger");

    static {
        try {
            FileHandler eventFileHandler = new FileHandler("reports/consumption_report.txt", false);
            eventFileHandler.setFormatter(new CustomFormatter());
            consumptionLogger.addHandler(eventFileHandler);
            consumptionLogger.setLevel(Level.INFO);

        } catch (IOException e) {
            System.err.println("Failed to initialize loggers: " + e.getMessage());
        }
    }
}
