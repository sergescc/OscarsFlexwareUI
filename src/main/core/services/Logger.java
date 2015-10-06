package main.core.services;

import java.io.PrintStream;
import java.util.Date;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Logger provides atomic log operations to the standard output and error streams
 *
 * @author Grant Moyer
 * @since 2015-10-06
 */
public class Logger {
    //Constants
    public static final int ERROR = -1;
    public static final int LOG = 0;

    private static class Entry {
        //Log data
        private long srcThreadID;
        private Date time;
        private int type;
        private String message;

        public Entry(long srcThreadID, int type, String message) {
            this.srcThreadID = srcThreadID;
            this.time = new Date();
            this.type = type;
            this.message = message;
        }

        public String toString() {
            String str = "[" + srcThreadID + "] " + time;
            if (type == Logger.LOG) {
                str += " L: ";
            } else {
                str += " E: ";
            }
            str += message;
            return str;
        }

        public PrintStream stream() {
            if (type == Logger.LOG) {
                return System.out;
            } else {
                return System.err;
            }
        }
    }

    private static boolean running = false;

    //entries that need to be logged
    private static LinkedBlockingQueue<Entry> entries = new LinkedBlockingQueue<>();

    //class cannot be instantiated
    private Logger() {}

    //add an Entry to the Queue
    public static void log(int type, String message) {
        //make sure Logger is running
        startLogger();

        long threadID = Thread.currentThread().getId();

        //the chance that the queue is full is incredibly low so BlockingQueue.put() doesn't have to be used
        entries.offer(new Entry(threadID, type, message));
    }

    //Runnable for logging thread
    private static class EntryLogger implements Runnable {
        @Override
        @SuppressWarnings("all")
        public void run() {
            //make sure logger stops on shutdown
            Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
                @Override
                public void run() {
                    Logger.interrupt();
                }
            }));

            Entry entry;
            //continuously take entries from queue until interrupted during shutdown
            try {
                while (true) {
                    entry = entries.take();
                    entry.stream().println(entry);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            //finish logging entries
            while ((entry = entries.poll()) != null) {
                entry.stream().println(entry);
            }
        }
    }

    private static Thread entryLoggerThread;
    //starts logger if it's not running, can only be called by class methods
    private static void startLogger() {
        if (!running) {
            entryLoggerThread = new Thread(new EntryLogger());
            running = true;
            entryLoggerThread.start();
        }
    }

    private static void interrupt() {
        if (running) {
            entryLoggerThread.interrupt();
        }
    }
}
