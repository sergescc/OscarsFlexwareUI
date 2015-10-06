package main.core.services;


import javax.annotation.Resource;
import javax.jms.*;

/**
 * The messenger class handles sending ad receiving messages from flexware
 *
 * @author Grant Moyer
 * @since 2015-10-06
 */
public class Messenger {
    @Resource(lookup = "jms/ConnectionFactory")
    private static ConnectionFactory connectionFactory;

    @Resource(lookup = "jms/Queue")
    private static Queue queue;

    private static Connection connection = null;
    private static Session session = null;

    public void initialize() {
        try {
            //create connection
            connection = connectionFactory.createConnection();

            //make sure connection closes
            Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Messenger.connection.close();
                    } catch (JMSException e) {
                        Logger.log(Logger.ERROR, "Exception caught while closing connection");
                        Logger.log(Logger.ERROR, "Exception: " + e + "\n");
                    }
                }
            }));
        } catch (JMSException e) {
            Logger.log(Logger.ERROR, "Exception caught while creating connection");
            Logger.log(Logger.ERROR, "Exception: " + e + "\n");
        }

        try {
            session = connection.createSession(true, 0);
        } catch(JMSException e) {
            Logger.log(Logger.ERROR, "Exception caught while creating session");
            Logger.log(Logger.ERROR, "Exception: " + e + "\n");
        }
    }

    private Messenger() {}

    public static void send() {

    }

    public static Message recv() {

    }
}
