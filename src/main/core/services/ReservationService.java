package main.core.services;

import main.core.entities.users.Reservation;

/**
 * Created by grant on 9/29/15.
 */
public interface ReservationService {
    Reservation createReservation(Reservation res);
    Reservation deleteReservation(long resID);
}
