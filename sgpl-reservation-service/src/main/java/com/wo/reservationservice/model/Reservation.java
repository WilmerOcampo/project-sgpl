package com.wo.reservationservice.model;

import com.wo.reservationservice.model.enums.EReservation;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "books")
@Getter
@Setter
public class Reservation extends Base {

    @Column(name = "reserve_cod", length = 8, nullable = false)
    private String ReserveCod; //title;
    //  RES0001

    @Column(name = "reserve_date", nullable = false)
    private LocalDateTime reserveDate; // Fecha de la reserva

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private EReservation status;

    @Column(name = "user_ids")
    private Long userId;

}
