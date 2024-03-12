package com.jtbank.backend.dto;

import com.jtbank.backend.constant.AccountType;
import jakarta.validation.constraints.NotNull;

public record AccountResponseDTO(
        long accountNumber,
        String accountHolderName,
        String aboutCustomer,
        String contactNumber,
        String accountEmail,
        double accountBalance,
        AccountType accountType
) {
}
