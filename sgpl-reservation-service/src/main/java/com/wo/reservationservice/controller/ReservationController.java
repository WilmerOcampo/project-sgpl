package com.wo.reservationservice.controller;

import com.wo.reservationservice.model.Reservation;
import com.wo.reservationservice.model.enums.EReservation;
import com.wo.reservationservice.service.IReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private IReservationService reservationService;

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Reservation>> getReservationsByStatus(@PathVariable EReservation status) {
        List<Reservation> reservations = reservationService.getReservationsByStatus(status);
        return ResponseEntity.ok(reservations);
    }

    @PostMapping
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation) {
        Reservation createdReservation = reservationService.createReservation(reservation);
        return ResponseEntity.ok(createdReservation);
    }

    @PutMapping("/cancel/{id}")
    public ResponseEntity<Void> cancelReservation(@PathVariable Long id) {
        reservationService.cancelReservation(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/finalize/{id}")
    public ResponseEntity<Void> finalizeReservation(@PathVariable Long id) {
        reservationService.finalizeReservation(id);
        return ResponseEntity.ok().build();
    }
}





