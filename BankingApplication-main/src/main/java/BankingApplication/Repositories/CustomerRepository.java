package BankingApplication.Repositories;


import BankingApplication.Model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByUserId(Long userId);

    Optional<Customer> findByUserUsername(String username);

    @Query("SELECT c FROM Customer c JOIN FETCH c.user WHERE c.user.username = :username")
    Optional<Customer> findByUsernameWithAccounts(String username);

    @Query("SELECT c FROM Customer c WHERE " +
            "LOWER(c.firstName) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(c.lastName) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(c.user.email) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<Customer> searchCustomers(String search, Pageable pageable);
}