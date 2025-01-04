package smarthome.reports;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggerManager {

    public static final Logger consumptionLogger = Logger.getLogger("ConsumptionLogger");
    public static final Logger eventLogger = Logger.getLogger("EventLogger");
    public static final Logger activityLogger = Logger.getLogger("ActivityLogger");

    static {
        try {
            FileHandler consumptionFileHandler = new FileHandler("reports/consumption_report.txt", false);
            consumptionFileHandler.setFormatter(new CustomFormatter());
            consumptionLogger.addHandler(consumptionFileHandler);
            consumptionLogger.setLevel(Level.INFO);

            FileHandler eventFileHandler = new FileHandler("reports/event_report.txt", false);
            eventFileHandler.setFormatter(new CustomFormatter());
            eventLogger.addHandler(eventFileHandler);
            eventLogger.setLevel(Level.INFO);

            FileHandler activityFileHandler = new FileHandler("reports/activity_report.txt", false);
            activityFileHandler.setFormatter(new CustomFormatter());
            activityLogger.addHandler(activityFileHandler);
            activityLogger.setLevel(Level.INFO);

        } catch (IOException e) {
            System.err.println("Failed to initialize loggers: " + e.getMessage());
        }
    }
}
