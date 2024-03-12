package com.jtbank.backend.service.impl;

import com.jtbank.backend.model.Account;
import com.jtbank.backend.repository.AccountRepository;
import com.jtbank.backend.service.IAccountService;
import com.jtbank.backend.utility.Helper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements IAccountService {
    private final AccountRepository accountRepository;

    @Override
    public Account createAccount(Account account) {
        account.setAccountNumber(Helper.generateAccountNumber());
        accountRepository.save(account);
        return account;
    }

    @Override
    public Account updateAccount(long accountNumber, Account account) {
        var existingAccount = getAccount(accountNumber);

        existingAccount.setAccountType(account.getAccountType());
        existingAccount.setAccountHolderName(account.getAccountHolderName());
        existingAccount.setContactNumber(account.getContactNumber());
        existingAccount.setAboutCustomer(account.getAboutCustomer());
        return accountRepository.save(existingAccount);
    }

    @Override
    public Account deleteAccount(long accountNumber) {
        var account = getAccount(accountNumber);
        accountRepository.delete(account);
        return account;
    }

    @Override
    public Account getAccount(long accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber).orElseThrow();
    }

    @Override
    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Account getAccountBySlNo(int slNo) {
        return accountRepository.findById(slNo).orElseThrow();
    }

    @Override
    public Account getAccountByEmail(String email) {
        return accountRepository.findByEmail(email).orElseThrow();
    }

    @Override
    public Account getAccountByEmailAndPassword(String email, String password) {
        return accountRepository.findByCredentialAccountEmailAndCredentialAccountPassword(email, password).orElseThrow();
    }

    @Override
    public void depositBalance(long accountNumber, double balance) {
        accountRepository.addBalance(accountNumber, balance);
    }

    @Override
    public void deductBalance(long accountNumber, double balance) {
        accountRepository.deductBalance(accountNumber, balance);
    }

    @Transactional
    @Override
    public void transfer(long sender, long receiver, double balance) {
        var receiverAccount = accountRepository.existsByAccountNumber(receiver);

        if (!receiverAccount) {
            throw new RuntimeException("receiver not found");
        }

        var senderAccount = getAccount(sender);
        var senderBalance = senderAccount.getAccountBalance();

        if (senderBalance < balance) {
            throw new RuntimeException("Insufficient balance");
        }
        accountRepository.addBalance(receiver, balance);
        accountRepository.deductBalance(sender, balance);
    }
}
