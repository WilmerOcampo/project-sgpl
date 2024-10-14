package com.wo.loanservice.respository;

import com.wo.loanservice.model.Loan;
import com.wo.loanservice.model.enums.ELoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILoanRepository extends JpaRepository<Loan, Long> {

    List<Loan> findByStatus(ELoan status);

    List<Loan> findAllByUserId(Long userId);

    List<Loan> findAllByBookId(Long bookId);

    // Listar préstamos con fecha de devolución vencida (fecha aproximada menor a la actual y estado activo)
    @Query("SELECT l FROM Loan l WHERE l.returnDateApprox < CURRENT_TIMESTAMP AND l.status = 'ACTIVO'")
    List<Loan> findLoansWithExpiredReturnDate();

    List<Loan> findAllByStatus(ELoan status);
}