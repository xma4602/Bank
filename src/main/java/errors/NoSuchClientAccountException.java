package errors;

public class NoSuchClientAccountException extends Exception{

    public final long accountNumber;
    public final long clientNumber;

    public NoSuchClientAccountException(long accountNumber, long clientNumber) {

        this.accountNumber = accountNumber;
        this.clientNumber = clientNumber;
    }

    @Override
    public String getMessage() {
        return "У клиента №%d отсутствует счет №%d".formatted(clientNumber, accountNumber);
    }
}
