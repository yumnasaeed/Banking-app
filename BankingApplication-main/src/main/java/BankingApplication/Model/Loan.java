package BankingApplication.Model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "loans", indexes = {
        @Index(name = "idx_loan_customer", columnList = "customer_id"),
        @Index(name = "idx_loan_status", columnList = "status")
})
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private LoanType loanType;

    @Column(nullable = false, precision = 19, scale = 4)
    private BigDecimal amount;

    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal interestRate;

    @Column(nullable = false)
    private Integer durationMonths;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    @Builder.Default
    private LoanStatus status = LoanStatus.PENDING;

    @Column(length = 255)
    private String rejectionReason;

    @Column(length = 500)
    private String purpose;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    private String approvedBy;
    private LocalDateTime approvedAt;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime appliedAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public enum LoanType {
        PERSONAL, HOME, CAR, EDUCATION, BUSINESS
    }

    public enum LoanStatus {
        PENDING, APPROVED, REJECTED, DISBURSED, CLOSED
    }

    public BigDecimal calculateMonthlyPayment() {
        if (interestRate == null || durationMonths == null || amount == null) return BigDecimal.ZERO;
        double rate = interestRate.doubleValue() / 100 / 12;
        int n = durationMonths;
        double amt = amount.doubleValue();
        double payment = (amt * rate * Math.pow(1 + rate, n)) / (Math.pow(1 + rate, n) - 1);
        return BigDecimal.valueOf(payment).setScale(2, java.math.RoundingMode.HALF_UP);
    }
}