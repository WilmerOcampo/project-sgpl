package com.wo.reservationservice.service;

import com.wo.reservationservice.model.Reservation;
import com.wo.reservationservice.model.enums.EReservation;
import com.wo.reservationservice.repository.IReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements IReservationService {

    @Autowired
    private IReservationRepository reservationRepository;

    @Override
    public List<Reservation> getReservationsByStatus(EReservation status) {
        return reservationRepository.findByStatus(status);
    }

    @Override
    public Reservation createReservation(Reservation reservation) {

        Long count = reservationRepository.count();
        long nextNumber = count + 1;
        String generatedCode = String.format("RES%05d", nextNumber);

        reservation.setReserveCod(generatedCode);
        reservation.setReserveDate(LocalDateTime.now());
        reservation.setStatus(EReservation.ACTIVO);

        //reservation.setCreatedBy(Long.valueOf("2"));

        return reservationRepository.save(reservation);
    }

    @Override
    public void cancelReservation(Long id) {
        Optional<Reservation> reservation = reservationRepository.findById(id);
        if (reservation.isPresent()) {
            Reservation res = reservation.get();
            res.setStatus(EReservation.CANCELADO);
            reservationRepository.save(res);
        }
    }

    @Override
    public void finalizeReservation(Long id) {
        Optional<Reservation> reservation = reservationRepository.findById(id);
        if (reservation.isPresent()) {
            Reservation res = reservation.get();
            res.setStatus(EReservation.FINALIZADO);
            reservationRepository.save(res);
        }
    }
}




