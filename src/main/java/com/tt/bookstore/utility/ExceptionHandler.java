package com.tt.bookstore.utility;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ExceptionHandler extends Exception {

    public ExceptionHandler() {
        formatter = new MyFormatter();
    }

    public ExceptionHandler(boolean exceptionLogToFile, Throwable throwable) {
        super(throwable);
        this.exceptionLogToFile = exceptionLogToFile;
    }
    private static final Logger LOG = Logger.getAnonymousLogger();
    private static FileHandler fh = null;
    private MyFormatter formatter;
    private @Value("${exception.printstrackon}")
    boolean isPrintStackTrace;
    private @Value("${exception.logfile}")
    boolean exceptionLogToFile;
    private @Value("${log.location}")
    String logLocation;

    public String writeLog(Throwable e) {        
        if (isPrintStackTrace) {
            e.printStackTrace();
        }
        if(exceptionLogToFile){
        	logToFile(e);
        }
        return "exception";
    }

    private void logToFile(Throwable e) {
        if (exceptionLogToFile) {
            try {                
                fh = new FileHandler(logLocation, true);
                fh.setFormatter(formatter);

                LOG.setUseParentHandlers(false);
                StringWriter sw = new StringWriter();
                e.printStackTrace(new PrintWriter(sw));
                LOG.addHandler(fh);
                LOG.info(sw.toString());

            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                if (fh != null) {
                    fh.close();
                }
            }
        }
    }


    private class MyFormatter extends Formatter {
        private final DateFormat DF = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss.SSS");
        @Override
        public String format(LogRecord record) {
            StringBuilder builder = new StringBuilder(1000);
            builder.append("Start:::-------------------------------------------------------------------------");
            builder.append("\n");            
            builder.append("---------------------------------------------------------------------------------");
            builder.append("\n");
            builder.append(DF.format(new Date(record.getMillis()))).append(" - ");
            builder.append("[").append(record.getSourceClassName()).append(".");
            builder.append(record.getSourceMethodName()).append("] - ");
            builder.append("[").append(record.getLevel()).append("] - ");
            builder.append(formatMessage(record));
            builder.append("\n");
            return builder.toString();
        }
    }
}