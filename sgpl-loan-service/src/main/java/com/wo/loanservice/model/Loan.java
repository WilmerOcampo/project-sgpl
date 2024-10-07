package com.wo.loanservice.model;

import com.wo.loanservice.model.enums.ELoan;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "loans")
@Getter
@Setter
public class Loan extends Base {

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "return_date_approx")
    private LocalDateTime returnDateApprox;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "return_date_real")
    private LocalDateTime returnDateReal;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private ELoan status;

    @Column(name = "user_ids")
    private Long userId;

    @Column(name = "user_ids")
    private Long bookId;

}
