package com.bank.errors;

public class TransferException extends Throwable {

    private final String message;
    public TransferException(Exception e) {
        message = "������ �������� �������: " + getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
