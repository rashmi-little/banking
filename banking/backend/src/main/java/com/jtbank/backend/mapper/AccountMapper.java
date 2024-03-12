package com.jtbank.backend.mapper;

import com.jtbank.backend.dto.AccountRequestDTO;
import com.jtbank.backend.dto.AccountResponseDTO;
import com.jtbank.backend.model.Account;
import com.jtbank.backend.model.Credential;
import org.springframework.beans.BeanUtils;

public class AccountMapper {
    private AccountMapper(){}

    public static Account modelMapper(AccountRequestDTO dto) {
        var credential = new Credential();
        BeanUtils.copyProperties(dto, credential);

        var account = new Account();
        BeanUtils.copyProperties(dto, account);
        account.setCredential(credential);

        return account;
    }

    public static AccountResponseDTO dtoMapper(Account account) {
        var accountNumber = account.getAccountNumber();
        var accountHolderName = account.getAccountHolderName();
        var aboutCustomer = account.getAboutCustomer();
        var contactNumber = account.getContactNumber();
        var accountType = account.getAccountType();

        var credential = account.getCredential();

        var customerEmail = credential.getAccountEmail();
        var accountBalance = account.getAccountBalance();

        return new AccountResponseDTO(
                accountNumber,
                accountHolderName,
                aboutCustomer,
                contactNumber,
                customerEmail,
                accountBalance,
                accountType);
    }
}
