package main.core.services;

import javax.jms.*;

/**
 * The Messenger class handles sending and receiving messages using JMS
 *
 * @author Grant Moyer
 * @since 2015-10-07
 */
public class Messenger {
    private Connection connection;
    private Session session;

    protected MessageProducer producer;
    protected MessageConsumer consumer;

    private MessageListener listener;

    /**
     * Initializes the JMS Session, MessageProducer, MessageConsumer, and MessageListener of the Messenger class
     *
     * @param connection The JMS Connection to use
     * @param local The local JMS Destination
     * @param remote The remote JMS Destination
     * @param listener the MessageListener to use when a message arrives
     */
    protected Messenger(Connection connection,
                        Destination local,
                        Destination remote,
                        MessageListener listener)
    {
        this.connection = connection;
        this.listener = listener;

        try {
            session = connection.createSession(true, 0);
            producer = session.createProducer(remote);
            consumer = session.createConsumer(local);
            consumer.setMessageListener(listener);
        } catch (JMSException e) {
            Logger.log(Logger.ERROR, "Exception caught while setting up MessageProducer and MessageConsumer");
            Logger.log(Logger.ERROR, "Exception: " + e);
        }
    }
}
