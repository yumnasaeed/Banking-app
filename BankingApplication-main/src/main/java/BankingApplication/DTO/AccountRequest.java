package BankingApplication.DTO;

import BankingApplication.Model.BankAccount;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

public class AccountRequest {

    @Data
    public static class CreateAccountRequest {
        @NotNull(message = "Account type is required")
        private BankAccount.AccountType accountType;

        @DecimalMin(value = "0.00", message = "Initial deposit cannot be negative")
        private BigDecimal initialDeposit = BigDecimal.ZERO;
    }

    @Data
    public static class DepositRequest {
        @NotBlank(message = "Account number is required")
        private String accountNumber;

        @NotNull(message = "Amount is required")
        @DecimalMin(value = "1.00", message = "Minimum deposit is 1.00")
        @DecimalMax(value = "1000000.00", message = "Maximum single deposit is 1,000,000.00")
        private BigDecimal amount;

        @Size(max = 255)
        private String description;
    }

    @Data
    public static class WithdrawalRequest {
        @NotBlank(message = "Account number is required")
        private String accountNumber;

        @NotNull(message = "Amount is required")
        @DecimalMin(value = "1.00", message = "Minimum withdrawal is 1.00")
        @DecimalMax(value = "100000.00", message = "Maximum single withdrawal is 100,000.00")
        private BigDecimal amount;

        @Size(max = 255)
        private String description;
    }

    @Data
    public static class TransferRequest {
        @NotBlank(message = "Source account number is required")
        private String sourceAccountNumber;

        @NotBlank(message = "Destination account number is required")
        private String destinationAccountNumber;

        @NotNull(message = "Amount is required")
        @DecimalMin(value = "1.00", message = "Minimum transfer is 1.00")
        @DecimalMax(value = "500000.00", message = "Maximum single transfer is 500,000.00")
        private BigDecimal amount;

        @Size(max = 255)
        private String description;
    }
}