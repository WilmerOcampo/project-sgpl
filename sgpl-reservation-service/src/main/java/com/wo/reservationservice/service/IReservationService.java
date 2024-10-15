package com.wo.reservationservice.service;

import com.wo.reservationservice.model.Reservation;
import com.wo.reservationservice.model.enums.EReservation;
import com.wo.reservationservice.payload.response.ReservationCodeResponse;
import com.wo.reservationservice.response.ReservationResponse;

import java.util.List;
import java.util.Optional;

public interface IReservationService {

    List<Reservation> findAll();
    List<ReservationResponse> findAll2();

    List<Reservation> getReservationsByStatus(EReservation status);

    Optional<Reservation> findByReserveCod(String reserveCod);

    ReservationCodeResponse createReservation(Reservation reservation);

    void cancelReservation(Long id/*, Long userId*/);

    void finalizeReservation(Long id/*, Long userId*/);

}

