package com.bank.errors;

public class InsufficientFundsException extends Exception {

    public final long accountNumber;
    public final long sum;

    public InsufficientFundsException(long accountNumber, long sum) {
        this.accountNumber = accountNumber;

        this.sum = sum;
    }

    @Override
    public String getMessage() {
        return "На счете №%d недостаточно средств, чтобы снять сумму %d".formatted(accountNumber, sum);
    }
}
