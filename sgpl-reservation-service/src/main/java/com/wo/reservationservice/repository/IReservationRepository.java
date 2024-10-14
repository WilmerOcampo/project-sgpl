package com.wo.reservationservice.repository;

import com.wo.reservationservice.model.Reservation;
import com.wo.reservationservice.model.enums.EReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByStatus(EReservation status);

    Optional<Reservation> findByReserveCod(String reserveCod);

}
