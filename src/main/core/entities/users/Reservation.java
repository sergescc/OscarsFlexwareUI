package main.core.entities.users;

import javax.persistence.*;
import java.util.List;

/**
 * Created by grant on 9/29/15.
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
}
