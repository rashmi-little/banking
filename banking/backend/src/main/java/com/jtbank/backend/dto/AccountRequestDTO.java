package com.jtbank.backend.dto;

import com.jtbank.backend.constant.AccountType;
import jakarta.validation.constraints.NotNull;

public record AccountRequestDTO(
        @NotNull
        String accountHolderName,
        @NotNull
        String aboutCustomer,
        @NotNull
        String contactNumber,
        @NotNull
        String accountEmail,
        @NotNull
        String accountPassword,
        @NotNull
        AccountType accountType
) {
}
