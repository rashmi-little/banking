package com.jtbank.backend.controller;

import com.jtbank.backend.dto.AccountRequestDTO;
import com.jtbank.backend.dto.AccountResponseDTO;
import com.jtbank.backend.mapper.AccountMapper;
import com.jtbank.backend.model.Credential;
import com.jtbank.backend.service.IAccountService;
import com.jtbank.backend.utility.Helper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final IAccountService iAccountService;
    @Autowired
    private Helper helper;

    @PostMapping
    public AccountResponseDTO createAccount(@RequestBody AccountRequestDTO accountRequestDTO) {
        var account = AccountMapper.modelMapper(accountRequestDTO);
        var response = iAccountService.createAccount(account);
        return AccountMapper.dtoMapper(response);
    }

    @PostMapping("/account/login")
    public AccountResponseDTO accountByAccountNumber(@RequestBody Credential credential) {
        var result = iAccountService.getAccountByEmailAndPassword(credential.getAccountEmail(), credential.getAccountPassword());
        return AccountMapper.dtoMapper(result);
    }

    @PutMapping("/account/{accountNumber}")
    public AccountResponseDTO updateAccount(@PathVariable long accountNumber, @RequestBody AccountRequestDTO accountRequestDTO) {
        var account = AccountMapper.modelMapper(accountRequestDTO);
        var response = iAccountService.updateAccount(accountNumber, account);
        return AccountMapper.dtoMapper(response);
    }


    @GetMapping
    public List<AccountResponseDTO> getAccounts() {

        var response = iAccountService.getAccounts().stream().map(account -> AccountMapper.dtoMapper(account)).toList();
        return response;
    }

    @GetMapping("/{slNo}")
    public AccountResponseDTO accountBySlNo(@PathVariable int slNo) {
        var result = iAccountService.getAccountBySlNo(slNo);
        return AccountMapper.dtoMapper(result);
    }

    @GetMapping("/number/{accountNumber}")
    public AccountResponseDTO accountByAccountNumber(@PathVariable int accountNumber) {
        var result = iAccountService.getAccount(accountNumber);
        return AccountMapper.dtoMapper(result);
    }

    @GetMapping("/account/{email}")
    public AccountResponseDTO accountByAccountEmail(@PathVariable String email) {
        var result = iAccountService.getAccountByEmail(email);
        return AccountMapper.dtoMapper(result);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/{accountNumber}")
    public AccountResponseDTO deleteByAccountNumber(@PathVariable long accountNumber) {
        var result = iAccountService.deleteAccount(accountNumber);
        return AccountMapper.dtoMapper(result);
    }

    @PatchMapping("deposite/{balance}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deposite(@RequestHeader long accountNumber, @PathVariable double balance) {
        iAccountService.depositBalance(accountNumber, balance);
    }

    @PatchMapping("deduct/{balance}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deduct(@RequestHeader long accountNumber, @PathVariable double balance) {
        iAccountService.deductBalance(accountNumber, balance);
    }

    @PatchMapping("/transfer/{receiver}/{balance}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void transfer(@RequestHeader long sender, @PathVariable long receiver, @PathVariable double balance) {
        iAccountService.transfer(sender, receiver, balance);
    }
}
