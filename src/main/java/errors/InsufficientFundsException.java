package errors;

public class InsufficientFundsException extends Exception {

    public final long accountNumber;
    public final int value;

    public InsufficientFundsException(long accountNumber, int value) {
        this.accountNumber = accountNumber;

        this.value = value;
    }

    @Override
    public String getMessage() {
        return "�� ����� �%d ������������ ������� ��� �������� �%d".formatted(accountNumber, value);
    }
}
