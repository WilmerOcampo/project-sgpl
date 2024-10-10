package com.wo.reservationservice.service;

import com.wo.reservationservice.model.Reservation;
import com.wo.reservationservice.model.enums.EReservation;

import java.util.List;

public interface IReservationService {

    List<Reservation> getReservationsByStatus(EReservation status);

    Reservation createReservation(Reservation reservation);

    void cancelReservation(Long id, Long userId);

    void finalizeReservation(Long id, Long userId);
}

