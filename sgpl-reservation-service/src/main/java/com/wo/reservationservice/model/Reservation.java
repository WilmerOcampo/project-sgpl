package com.wo.reservationservice.model;

import com.wo.reservationservice.model.enums.EReservation;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "reservations")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Reservation extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(name = "reserve_cod", length = 8, nullable = false)
    private String reserveCod;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private EReservation status;

    @Column(name = "user_id")
    private Long userId;

}
