package BankingApplication.Repositories;

import BankingApplication.Model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Optional<Transaction> findByTransactionReference(String reference);

    @Query("SELECT t FROM Transaction t WHERE " +
            "(t.sourceAccount.accountNumber = :accountNumber OR t.destinationAccount.accountNumber = :accountNumber) " +
            "ORDER BY t.transactionDate DESC")
    Page<Transaction> findByAccountNumber(@Param("accountNumber") String accountNumber, Pageable pageable);

    @Query("SELECT t FROM Transaction t WHERE " +
            "(t.sourceAccount.accountNumber = :accountNumber OR t.destinationAccount.accountNumber = :accountNumber) " +
            "AND t.transactionDate BETWEEN :startDate AND :endDate " +
            "ORDER BY t.transactionDate DESC")
    Page<Transaction> findByAccountNumberAndDateRange(
            @Param("accountNumber") String accountNumber,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            Pageable pageable);

    @Query("SELECT t FROM Transaction t WHERE " +
            "t.sourceAccount.customer.user.username = :username OR " +
            "t.destinationAccount.customer.user.username = :username " +
            "ORDER BY t.transactionDate DESC")
    Page<Transaction> findByUsername(@Param("username") String username, Pageable pageable);

    @Query("SELECT t FROM Transaction t WHERE " +
            "(t.sourceAccount.customer.user.username = :username OR t.destinationAccount.customer.user.username = :username) " +
            "AND t.transactionDate BETWEEN :startDate AND :endDate " +
            "ORDER BY t.transactionDate DESC")
    Page<Transaction> findByUsernameAndDateRange(
            @Param("username") String username,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            Pageable pageable);
}