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

        String reserveCode = generateReserveCode();
        reservation.setReserveCod(reserveCode);
        reservation.setReserveDate(LocalDateTime.now());
        reservation.setStatus(EReservation.ACTIVO);

        //reservation.setCreatedBy(Long.valueOf("2"));

        return reservationRepository.save(reservation);
    }

    @Override
    public void cancelReservation(Long id) {
        updateReservationStatus(id, EReservation.CANCELADO);
    }

    @Override
    public void finalizeReservation(Long id) {
        updateReservationStatus(id, EReservation.FINALIZADO);
    }

    private String generateReserveCode() {
        //Long count = reservationRepository.count();
        //return String.format("RES%05d", count + 1);
        return "RES" + String.format("%05d", (int) (Math.random() * 99999));
    }

    public class ReservationNotFoundException extends RuntimeException {
        public ReservationNotFoundException(Long id) {
            super("Reserva con ID " + id + " no encontrada");
        }
    }

    private void updateReservationStatus(Long id, EReservation status) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ReservationNotFoundException(id));
        reservation.setStatus(status);
        reservationRepository.save(reservation);
    }

}




