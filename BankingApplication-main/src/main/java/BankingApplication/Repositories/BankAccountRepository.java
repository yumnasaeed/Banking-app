package BankingApplication.Repositories;

import BankingApplication.Model.BankAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jakarta.persistence.LockModeType;
import java.util.List;
import java.util.Optional;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {

    Optional<BankAccount> findByAccountNumber(String accountNumber);

    List<BankAccount> findByCustomerId(Long customerId);

    List<BankAccount> findByCustomerUserUsername(String username);

    Page<BankAccount> findByCustomerId(Long customerId, Pageable pageable);

    boolean existsByAccountNumber(String accountNumber);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT a FROM BankAccount a WHERE a.accountNumber = :accountNumber")
    Optional<BankAccount> findByAccountNumberForUpdate(String accountNumber);

    @Query("SELECT COUNT(a) FROM BankAccount a WHERE a.customer.id = :customerId")
    long countByCustomerId(Long customerId);
}