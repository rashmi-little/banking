package com.jtbank.backend.utility;

import org.springframework.stereotype.Component;

@Component
public class Helper {
    private Helper() {
    }

    public static long generateAccountNumber() {
        long randomNumber = (long) (Math.random() * 10_00_00_000l);
        long result = 1_00_00_00_000l + randomNumber;
        return result;
    }
}
