package main.core.services;


import javax.annotation.Resource;
import javax.jms.*;
import java.io.Serializable;

/**
 * The messenger class handles sending ad receiving messages from flexware
 *
 * @author Grant Moyer
 * @since 2015-10-06
 */
abstract public class Messenger implements MessageListener {
    @Resource(lookup = "jms/ConnectionFactory")
    private static ConnectionFactory connectionFactory;

    @Resource(lookup = "jms/UIQueue")
    private static Queue uiQueue;

    @Resource(lookup = "jms/FlexQueue")
    private static Queue flexQueue;

    protected static Connection connection = null;
    protected static Session session = null;
    protected static MessageProducer producer = null;
    protected static MessageConsumer consumer = null;

    private static boolean initialized = false;

    private static void initialize() {
        if (!initialized) {
            initialized = true;

            if (connection == null) {
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
                                Logger.log(Logger.ERROR, "Exception: " + e);
                            }
                        }
                    }));

                    if (session == null) {
                        try {
                            session = connection.createSession(true, 0);

                            if (producer == null) {
                                try {
                                    producer = session.createProducer(flexQueue);
                                } catch (JMSException e) {
                                    Logger.log(Logger.ERROR, "Exception caught while creating message producer");
                                    Logger.log(Logger.ERROR, "Exception: " + e);
                                }
                            }

                            if (consumer == null) {
                                try {
                                    consumer = session.createConsumer(uiQueue);
                                } catch (JMSException e) {
                                    Logger.log(Logger.ERROR, "Exception caught while creating message consumer");
                                    Logger.log(Logger.ERROR, "Exception: " + e);
                                }
                            }
                        } catch (JMSException e) {
                            Logger.log(Logger.ERROR, "Exception caught while creating session");
                            Logger.log(Logger.ERROR, "Exception: " + e);
                            initialized = false;
                        }
                    }
                } catch (JMSException e) {
                    Logger.log(Logger.ERROR, "Exception caught while creating connection");
                    Logger.log(Logger.ERROR, "Exception: " + e);
                    initialized = false;
                }
            }
        }
    }

    private Messenger() {}

    static void send(Serializable obj) {
        //lazily initialize Messenger
        initialize();

        try {
            ObjectMessage message = session.createObjectMessage(obj);
            producer.send(message);
        } catch (JMSException e) {
            Logger.log(Logger.ERROR, "Exception caught while creating message to send");
            Logger.log(Logger.ERROR, "Exception: " + e);
        }

    }
}
