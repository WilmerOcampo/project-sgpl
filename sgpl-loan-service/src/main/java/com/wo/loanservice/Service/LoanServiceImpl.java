package com.wo.loanservice.service;

import com.wo.loanservice.model.Loan;
import com.wo.loanservice.respository.ILoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoanServiceImpl implements ILoanService{

    private final ILoanRepository loanRepository;

    @Override
    public List<Loan> getAllLoans(){
        return loanRepository.findAll();
    }

    @Override
    public Loan generateLoan(Loan loan){
        return loanRepository.save(loan);
    }

}