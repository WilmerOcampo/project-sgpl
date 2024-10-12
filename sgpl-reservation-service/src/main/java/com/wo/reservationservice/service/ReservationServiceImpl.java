package com.wo.reservationservice.service;

import com.wo.reservationservice.exeption.ReservationNotFoundException;
import com.wo.reservationservice.model.Reservation;
import com.wo.reservationservice.model.enums.EReservation;
import com.wo.reservationservice.payload.response.ReservationResponse;
import com.wo.reservationservice.repository.IReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl implements IReservationService {

    private final IReservationRepository reservationRepository;

    @Autowired
    public ReservationServiceImpl(IReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    @Override
    public List<Reservation> getReservationsByStatus(EReservation status) {
        return reservationRepository.findByStatus(status);
    }

    @Override
    public ReservationResponse createReservation(Reservation reservation) {
        reservation.setReserveCod(generateReserveCode());
        reservation.setStatus(EReservation.ACTIVO);
        Reservation savedReservation = reservationRepository.save(reservation);
        return new ReservationResponse("Código de Reserva: " + savedReservation.getReserveCod());
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
        return "RES" + String.format("%05d", (int) (Math.random() * 99999));
    }

    private void updateReservationStatus(Long id, EReservation status) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ReservationNotFoundException(id));
        reservation.setStatus(status);
        reservationRepository.save(reservation);
    }

}




