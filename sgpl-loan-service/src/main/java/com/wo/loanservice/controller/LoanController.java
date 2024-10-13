package com.wo.loanservice.controller;

import com.wo.loanservice.model.Loan;
import com.wo.loanservice.service.ILoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/loan")
@RequiredArgsConstructor
@CrossOrigin
public class LoanController{

    private final ILoanService loanService;

    @GetMapping
    public List<Loan> getAllLoans(){
        return loanService.getAllLoans();
    }

    @PostMapping
    public Loan generateLoan(@RequestBody Loan loan){
        return loanService.generateLoan(loan);
    }

}