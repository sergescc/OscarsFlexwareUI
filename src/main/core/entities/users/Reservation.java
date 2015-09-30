package main.core.entities.users;

import javax.persistence.*;
import java.util.List;

/**
 * A Reservation entry in a database using JPA.
 * <p>
 * A Reservation is set of requests for paths that have been scheduled by OSCARS.
 *
 * @author Grant Moyer
 * @since 2015-09-29
 */
public class Reservation {
    //Reservation ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long resID;

    //List of Requests
    @OneToMany(mappedBy="reservation")
    protected List<Request> requests;

    //ID of User who made Reservation
    protected long userID;

    //-----------------------------------------------------------
    // Getters
    //-----------------------------------------------------------

    /**
     * Reservation ID getter
     * @return Reservation ID
     */
    public long getResID() {
        return resID;
    }

    /**
     * Request List getter
     * @return List of Requests in Reservation
     */
    public List<Request> getRequests() {
        return requests;
    }

    /**
     * User ID getter
     * @return ID of User Reservation belongs to
     */
    public long getUserID() {
        return userID;
    }
}
