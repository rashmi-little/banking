package com.jtbank.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class Credential {
    @Column(unique = true, nullable = false, length = 50)
    private String accountEmail;
    @Column(nullable = false)
    private String accountPassword;
}
