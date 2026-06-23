package BankingApplication.DTO;

import BankingApplication.Model.Loan;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class LoanRequest {

    @NotNull(message = "Loan type is required")
    private Loan.LoanType loanType;

    @NotNull(message = "Loan amount is required")
    @DecimalMin(value = "1000.00", message = "Minimum loan amount is 1,000.00")
    @DecimalMax(value = "10000000.00", message = "Maximum loan amount is 10,000,000.00")
    private BigDecimal amount;

    @NotNull(message = "Duration is required")
    @Min(value = 1, message = "Minimum duration is 1 month")
    @Max(value = 360, message = "Maximum duration is 360 months (30 years)")
    private Integer durationMonths;

    @Size(max = 500, message = "Purpose cannot exceed 500 characters")
    private String purpose;
}