package main.core.entities.users;

import javax.persistence.*;

/**
 * Created by grant on 9/29/15.
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

}
