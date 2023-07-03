package com.bank.errors;

public class TransferException extends Exception {

    private final String message;

    public TransferException(Exception e) {
        message = "Transfer error: " + e.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
