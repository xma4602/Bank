package errors;

public class NoSuchClientException extends Exception {

    public final long clientNumber;

    public NoSuchClientException(long clientNumber) {
        this.clientNumber = clientNumber;
    }

    @Override
    public String getMessage() {
        return "Не существует клиента №%d".formatted(clientNumber);
    }
}
