package com.wo.loanservice.controller;

import com.wo.loanservice.model.Loan;
import com.wo.loanservice.model.enums.ELoan;
import com.wo.loanservice.payload.OperationResponse;
import com.wo.loanservice.service.ILoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loan")
@RequiredArgsConstructor
@CrossOrigin
public class LoanController {

    private final ILoanService loanService;

    @GetMapping("/all")
    public ResponseEntity<List<Loan>> getAllLoans() {
        return ResponseEntity.ok(loanService.getAllLoans());
    }

    @PostMapping("/save")
    public ResponseEntity<Loan> generateLoan(@RequestBody Loan loan) {
        return ResponseEntity.ok(loanService.generateLoan(loan));
    }

    @GetMapping("/expired") //Prestamos con fecha de devolucion vencida
    public List<Loan> getExpiredLoans() {
        return loanService.findLoansWithExpiredReturnDate();
    }

    @GetMapping("/status/{status}") //Prestamos con estado activo o finalizado
    public List<Loan> getLoansByStatus(@PathVariable ELoan status) {
        return loanService.findAllByStatus(status);
    }

    @PutMapping("/finalize/{id}")
    public OperationResponse finalizeLoan(@PathVariable Long id) {
        return loanService.finalize(id);
    }
}