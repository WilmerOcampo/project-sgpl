package com.wo.loanservice.service;

import com.wo.loanservice.model.Loan;

import java.util.List;

public interface ILoanService {
    List<Loan> getAllLoans();
    Loan generateLoan(Loan loan);
}