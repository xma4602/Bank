package com.bank.errors;

import java.util.UUID;

public class NoSuchClientAccountException extends Exception{

    public final UUID accountId;
    public final UUID clientId;

    public NoSuchClientAccountException(UUID accountId, UUID clientId) {
        this.accountId = accountId;
        this.clientId = clientId;
    }

    public NoSuchClientAccountException(UUID clientId) {
        this(null, clientId);
    }

    @Override
    public String getMessage() {
        if (accountId != null){
            return "Client \"%s\" has no account \"%s\"".formatted(clientId, accountId);
        }
        else {
            return "Client \"%s\" has no accounts".formatted(clientId);
        }
    }
}
