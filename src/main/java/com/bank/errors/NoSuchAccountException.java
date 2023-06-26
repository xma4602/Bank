package com.bank.errors;

public class NoSuchAccountException extends Exception {
    public final long accountNumber;

    public NoSuchAccountException(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public String getMessage() {
        return "�� ���������� ����� �%d".formatted(accountNumber);
    }
}
