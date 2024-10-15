package com.wo.loanservice.service;

import com.wo.loanservice.model.Loan;
import com.wo.loanservice.model.enums.ELoan;
import com.wo.loanservice.payload.OperationResponse;

import java.util.List;
import java.util.Optional;

public interface ILoanService {

    List<Loan> getAllLoans();

    List<Loan> findByUserId(Long userId);

    List<Loan> findByBookId(Long bookId);

    List<Loan> findAllByStatus(ELoan status);

    OperationResponse finalize(Long id);

    Optional<Loan> findById(Long id);

    Loan generateLoan(Loan loan);

    List<Loan> findLoansWithExpiredReturnDate();

}
