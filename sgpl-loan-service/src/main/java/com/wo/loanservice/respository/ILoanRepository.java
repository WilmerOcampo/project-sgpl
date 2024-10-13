package com.wo.loanservice.respository;

import com.wo.loanservice.model.Loan;
import com.wo.loanservice.model.enums.ELoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILoanRepository extends JpaRepository<Loan, Long> {

    List<Loan> findByStatus(ELoan status);

    List<Loan> findAllByUserId(Long userId);

    List<Loan> findAllByBookId(Long bookId);

}