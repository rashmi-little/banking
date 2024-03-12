package com.jtbank.backend.repository;

import com.jtbank.backend.model.Account;
import jakarta.websocket.server.PathParam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findByAccountNumber(long accountNumber);

    Optional<Account> findByCredentialAccountEmailAndCredentialAccountPassword(String email, String password);

    //    @Query(nativeQuery = true, value = "select * from account_table where account_email = ?")
    @Query(value = "SELECT a FROM Account a where a.credential.accountEmail = :email")
    Optional<Account> findByEmail(@PathParam("email") String email);

    @Query("update Account a set a.accountBalance = a.accountBalance + :balance where a.accountNumber = :accountNumber")
    @Modifying
    @Transactional
    int addBalance(@PathParam("accountNumber") long accountNumber, @PathParam("balance") double balance);

    @Query("update Account a set a.accountBalance = a.accountBalance - :balance where a.accountNumber = :accountNumber")
    @Modifying
    @Transactional
    int deductBalance(@PathParam("accountNumber") long accountNumber, @PathParam("balance") double balance);

    boolean existsByAccountNumber(long accountNumber);
}
