package com.wo.reservationservice.exeption;

public class ReservationNotFoundException extends RuntimeException {

    public ReservationNotFoundException(Long id) {
        super("Reserva con ID " + id + " no encontrada");
    }

}
