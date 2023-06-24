import errors.InsufficientFundsException;
import errors.NoSuchAccountException;

public class Account {
    protected int amount;
    protected long number;

    public Account(long number, int amount) {
        this.number = number;
        this.amount = amount;
    }

    public Account(long number) {
        this(number, 0);
    }

    public static Account open(long clientNumber) {
        return AccountService.open(clientNumber);
    }

    public void close() throws NoSuchAccountException {
        AccountService.close(number);
    }

    public void put(int value) {
        amount = AccountService.put(number, value);
    }

    public void take(int value) throws InsufficientFundsException {
        if (amount < value) throw new InsufficientFundsException(number, value);
        amount = AccountService.take(number, value);
    }

    public void transfer(long payeeAccountNumber, int value) throws InsufficientFundsException, NoSuchAccountException {
        Account acc = AccountService.getAccount(payeeAccountNumber);
        take(value);
        acc.put(value);
    }

    public long getNumber() {
        return number;
    }

    public int getAmount() {
        return amount;
    }
}
