package com.bank.errors;

import java.util.UUID;

public class InsufficientFundsException extends Exception {

    public final UUID accountId;
    public final long sum;

    public InsufficientFundsException(UUID accountId, long sum) {
        this.accountId = accountId;
        this.sum = sum;
    }

    @Override
    public String getMessage() {
        return "There are not enough funds on account \"%s\" to take the amount %d".formatted(accountId, sum);
    }
}
