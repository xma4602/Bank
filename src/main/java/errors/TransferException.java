package errors;

public class TransferException extends Throwable {

    private final String message;
    public TransferException(Exception e) {
        message = "Ошибка перевода средств: " + getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
