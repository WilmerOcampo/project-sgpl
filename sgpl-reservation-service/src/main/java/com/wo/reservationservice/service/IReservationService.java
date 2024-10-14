package com.wo.reservationservice.service;

import com.wo.reservationservice.model.Reservation;
import com.wo.reservationservice.model.enums.EReservation;
import com.wo.reservationservice.payload.response.ReservationResponse;

import java.util.List;
import java.util.Optional;

public interface IReservationService {

    List<Reservation> findAll();

    List<Reservation> getReservationsByStatus(EReservation status);

    Optional<Reservation> findByReserveCod(String reserveCod);

    ReservationResponse createReservation(Reservation reservation);

    void cancelReservation(Long id/*, Long userId*/);

    void finalizeReservation(Long id/*, Long userId*/);

}

