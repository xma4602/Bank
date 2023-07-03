package com.bank.errors;

import java.util.UUID;

public class NoSuchClientException extends Exception {

    public final UUID clientId;

    public NoSuchClientException(UUID clientId) {
        this.clientId = clientId;
    }

    @Override
    public String getMessage() {
        return "There is no client with a id \"%s\"".formatted(clientId);
    }
}
