package main.core.entities.users;

import javax.persistence.*;

public class User {
    //User name
    protected String name;

    //User ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long userID;
}
