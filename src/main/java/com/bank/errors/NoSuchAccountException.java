package com.bank.errors;

import java.util.UUID;

public class NoSuchAccountException extends Exception {
    public final UUID accountId;

    public NoSuchAccountException(UUID accountId) {
        this.accountId = accountId;
    }

    @Override
    public String getMessage() {
        return "There is no account with a id \"%s\"".formatted(accountId);
    }
}
