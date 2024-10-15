package com.wo.reservationservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wo.reservationservice.kafka.BookResponse;
import com.wo.reservationservice.kafka.producer.KafkaProducer;
import com.wo.reservationservice.model.Reservation;
import com.wo.reservationservice.model.enums.EReservation;
import com.wo.reservationservice.payload.response.ReservationCodeResponse;
import com.wo.reservationservice.response.ReservationResponse;
import com.wo.reservationservice.service.IReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservation")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ReservationController {

    private final IReservationService reservationService;
    private final KafkaProducer kafkaProducer;

    @Autowired
    public ReservationController(IReservationService reservationService, KafkaProducer kafkaProducer) {
        this.reservationService = reservationService;
        this.kafkaProducer = kafkaProducer;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Reservation>> getAllReservations() {
        List<Reservation> reservations = reservationService.findAll();
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/all2")
    public ResponseEntity<List<ReservationResponse>> getAllReservations2() {
        List<ReservationResponse> reservations = reservationService.findAll2();
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Reservation>> getReservationsByStatus(@PathVariable EReservation status) {
        List<Reservation> reservations = reservationService.getReservationsByStatus(status);
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/{code}")
    public ResponseEntity<Optional<Reservation>> getReservationsByStatus(@PathVariable("code") String code) {
        Optional<Reservation> reservation = reservationService.findByReserveCod(code);
        return ResponseEntity.ok(reservation);
    }

    @PostMapping("/generate")
    public ResponseEntity<ReservationCodeResponse> createReservation(@RequestBody Reservation reservation) {
        ReservationCodeResponse response = reservationService.createReservation(reservation);
        return ResponseEntity.ok(response);
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

    @GetMapping("/k/{id}")
    public ResponseEntity<?> idMessage(@PathVariable Long id) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String message = mapper.writeValueAsString(id);

        kafkaProducer.sendIdMessage(message);

        return ResponseEntity.ok("Successfully, message send to KafkaClient Producer: " + message);
    }
}
