package main.core.entities.users;

import javax.persistence.*;

/**
 * A Request entry in a datbase using JPA.
 * <p>
 * A Request is a connection that has been made by OSCARS as part of a Reservation.
 *
 * @author Grant Moyer
 * @since 2015-09-29
 */
public class Request {
    //Request ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long reqID;

    //Reservation the request belongs to
    @ManyToOne
    protected Reservation reservation;

    //Source URN
    protected String src;

    //Destination URN
    protected String dest;

    //Bandwidth
    protected double bandwidth;

    //Start time
    protected long start;

    //End time
    protected long end;

    //-----------------------------------------------------------
    // Getters
    //-----------------------------------------------------------

    /**
     * Request ID getter
     * @return Request ID
     */
    public long getReqID() {
        return reqID;
    }

    /**
     * Parent Reservation getter
     * @return The Reservation that this Request belongs to
     */
    public Reservation getReservation() {
        return reservation;
    }

    /**
     * Source URN getter
     * @return The URN for the source node of this connection
     */
    public String getSrc() {
        return src;
    }

    /**
     * Destination URN getter
     * @return the URN for the destination node of this connection
     */
    public String getDest() {
        return dest;
    }

    /**
     * Bandwidth getter
     * @return The garunteed bandwidth of this connection
     */
    public double getBandwidth() {
        return bandwidth;
    }

    /**
     * Start time getter
     * @return The time at which the connection starts
     */
    public long getStart() {
        return start;
    }

    /**
     * End time getter
     * @return The time at which the connection ends
     */
    public long getEnd() {
        return end;
    }
}
