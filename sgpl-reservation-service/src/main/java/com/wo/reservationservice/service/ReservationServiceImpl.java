package com.wo.reservationservice.service;

import com.wo.reservationservice.exeption.ReservationNotFoundException;
import com.wo.reservationservice.model.Reservation;
import com.wo.reservationservice.model.enums.EReservation;
import com.wo.reservationservice.payload.response.ReservationCodeResponse;
import com.wo.reservationservice.repository.IReservationRepository;
import com.wo.reservationservice.response.ReservationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public List<ReservationResponse> findAll2() {
        return List.of();
    }

    @Override
    public List<Reservation> getReservationsByStatus(EReservation status) {
        return reservationRepository.findByStatus(status);
    }

    @Override
    public Optional<Reservation> findByReserveCod(String reserveCod) {
        return reservationRepository.findByReserveCod(reserveCod);
    }

    @Override
    public ReservationCodeResponse createReservation(Reservation reservation) {
        reservation.setReserveCod(generateReserveCode());
        reservation.setStatus(EReservation.ACTIVO);
        Reservation savedReservation = reservationRepository.save(reservation);
        return new ReservationCodeResponse("Código de Reserva: " + savedReservation.getReserveCod());
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




