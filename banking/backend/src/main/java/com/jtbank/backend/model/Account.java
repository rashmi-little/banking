package com.jtbank.backend.model;

import com.jtbank.backend.constant.AccountType;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name="account_table")
public class Account extends Auditing{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accountSlNo;
    @Column(unique = true)
    private long accountNumber;
    @Column(name = "account_name")
    private String accountHolderName;

    @Column(nullable = false)
    private String contactNumber;
    @Lob
//    String for character and byte[] for binary
    private String aboutCustomer;
    @Embedded
    private Credential credential;
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    private double accountBalance;
}