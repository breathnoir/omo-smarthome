package smarthome.reports;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class CustomFormatter extends Formatter {
    @Override
    public String format(LogRecord record) {
        //return the log message without the default metadata
        return record.getMessage() + System.lineSeparator();
    }
}