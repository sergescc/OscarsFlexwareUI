package main.core.services;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.MessageListener;

/**
 * Handles messaging from UI to Flexware
 *
 * @author Grant Moyer
 * @since 2015-10-07
 */
public class UIMessenger extends Messenger {
    protected UIMessenger(Connection connection, Destination local, Destination remote, MessageListener listener) {
        super(connection, local, remote, listener);
    }
}
