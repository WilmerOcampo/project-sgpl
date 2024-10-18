package com.wo.loanservice.model;

import com.wo.loanservice.model.enums.ELoan;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "loans")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Loan extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(name = "return_date_approx")
    private LocalDate returnDateApprox;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "return_date_real")
    private LocalDateTime returnDateReal;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private ELoan status;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "book_id")
    private Long bookId;

}
