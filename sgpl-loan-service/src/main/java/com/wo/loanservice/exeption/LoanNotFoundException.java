package com.wo.loanservice.exeption;

public class LoanNotFoundException extends RuntimeException {

    public LoanNotFoundException(Long id) {
        super("Préstamo con ID: " + id + " no encontrada");
    }

}
