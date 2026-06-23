package BankingApplication.DTO;

import BankingApplication.Model.BankAccount;
import BankingApplication.Model.Loan;
import BankingApplication.Model.Transaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ResponseDTOs {

    @Data @Builder @NoArgsConstructor @AllArgsConstructor
    public static class AuthResponse {
        private String accessToken;
        private String tokenType = "Bearer";
        private long expiresIn;
        private String username;
        private String role;
    }

    @Data @Builder @NoArgsConstructor @AllArgsConstructor
    public static class CustomerResponse {
        private Long id;
        private String firstName;
        private String lastName;
        private String email;
        private String username;
        private String phone;
        private String address;
        private LocalDateTime createdAt;
    }

    @Data @Builder @NoArgsConstructor @AllArgsConstructor
    public static class AccountResponse {
        private Long id;
        private String accountNumber;
        private BankAccount.AccountType accountType;
        private BigDecimal balance;
        private BankAccount.AccountStatus status;
        private Long customerId;
        private String customerName;
        private LocalDateTime createdAt;
    }

    @Data @Builder @NoArgsConstructor @AllArgsConstructor
    public static class TransactionResponse {
        private Long id;
        private String transactionReference;
        private Transaction.TransactionType transactionType;
        private BigDecimal amount;
        private String sourceAccountNumber;
        private String destinationAccountNumber;
        private String description;
        private Transaction.TransactionStatus status;
        private LocalDateTime transactionDate;
    }

    @Data @Builder @NoArgsConstructor @AllArgsConstructor
    public static class LoanResponse {
        private Long id;
        private Loan.LoanType loanType;
        private BigDecimal amount;
        private BigDecimal interestRate;
        private Integer durationMonths;
        private BigDecimal monthlyPayment;
        private Loan.LoanStatus status;
        private String purpose;
        private String rejectionReason;
        private Long customerId;
        private String customerName;
        private LocalDateTime appliedAt;
        private LocalDateTime approvedAt;
        private String approvedBy;
    }

    @Data @Builder @NoArgsConstructor @AllArgsConstructor
    public static class ApiResponse<T> {
        private boolean success;
        private String message;
        private T data;
        private LocalDateTime timestamp = LocalDateTime.now();

        public static <T> ApiResponse<T> success(String message, T data) {
            return ApiResponse.<T>builder()
                    .success(true)
                    .message(message)
                    .data(data)
                    .timestamp(LocalDateTime.now())
                    .build();
        }

        public static <T> ApiResponse<T> error(String message) {
            return ApiResponse.<T>builder()
                    .success(false)
                    .message(message)
                    .timestamp(LocalDateTime.now())
                    .build();
        }
    }

    @Data @Builder @NoArgsConstructor @AllArgsConstructor
    public static class PageResponse<T> {
        private java.util.List<T> content;
        private int pageNumber;
        private int pageSize;
        private long totalElements;
        private int totalPages;
        private boolean last;
    }
}