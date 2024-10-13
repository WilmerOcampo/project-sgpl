package com.wo.loanservice.respository;

import com.wo.bookservice.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ILoanRepository extends JpaRepository<Loan, Long>{
}