package main.core.entities.users;

import javax.persistence.*;

/**
 * A User entry in a database using JPA.
 * <p>
 * Reservations made with OSCARS are grouped in this UI by the User who made them.
 *
 * @author Grant Moyer
 * @since 2015-09-29
 */
public class User {
    //User name
    protected String name;

    //User ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long userID;

    //-----------------------------------------------------------
    // Getters
    //-----------------------------------------------------------

    /**
     * User name getter
     * @return The name of a user
     */
    public String getName() {
        return name;
    }

    /**
     * User ID getter
     * @return The ID of a user
     */
    public long getUserID() {
        return userID;
    }
}
