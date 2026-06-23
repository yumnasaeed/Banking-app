package BankingApplication.Repositories;

import BankingApplication.Model.Loan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

    List<Loan> findByCustomerId(Long customerId);

    Page<Loan> findByCustomerId(Long customerId, Pageable pageable);

    Page<Loan> findByCustomerUserUsername(String username, Pageable pageable);

    Page<Loan> findByStatus(Loan.LoanStatus status, Pageable pageable);

    long countByCustomerIdAndStatus(Long customerId, Loan.LoanStatus status);
}