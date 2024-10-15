package com.wo.loanservice.service;

import com.wo.loanservice.model.Loan;
import com.wo.loanservice.model.enums.ELoan;
import com.wo.loanservice.payload.OperationResponse;
import com.wo.loanservice.respository.ILoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoanServiceImpl implements ILoanService {

    private final ILoanRepository loanRepository;

    @Override
    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    @Override
    public List<Loan> findByUserId(Long userId) {
        return loanRepository.findAllByUserId(userId);
    }

    @Override
    public List<Loan> findByBookId(Long bookId) {
        return loanRepository.findAllByBookId(bookId);
    }

    @Override
    public List<Loan> findAllByStatus(ELoan status) {
        return loanRepository.findByStatus(status);
    }

    @Override
    public Optional<Loan> findById(Long id) {
        return Optional.ofNullable(loanRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Could not find")));
    }

    @Override
    public Loan generateLoan(Loan loan) {
        return loanRepository.save(loan);
    }

    @Override
    public List<Loan> findLoansWithExpiredReturnDate() {
        return loanRepository.findLoansWithExpiredReturnDate();
    }

    @Override
    public OperationResponse finalize(Long id) {
        Optional<Loan> loan = this.findById(id);
        try {
            if (loan.isEmpty()) {
                return new OperationResponse("Could not find loan");
            }
            loan.get().setStatus(ELoan.FINALIZADO);
            loanRepository.save(loan.get());
            return new OperationResponse("Loan completed successfully");
        } catch (Exception e) {
            return new OperationResponse(e.getLocalizedMessage());
        }
    }

}