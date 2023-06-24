import errors.InsufficientFundsException;
import errors.NoSuchAccountException;
import errors.NoSuchClientAccountException;
import errors.TransferException;

import java.util.List;
import java.util.Optional;

public class Client {
    String name;

    long number;
    List<Account> accounts;

    protected Client(long number, String name, List<Account> accounts) {
        this.number = number;
        this.name = name;
        this.accounts = accounts;
    }

    protected Optional<Account> find(long accountNumber) {
        return accounts.stream().filter(x -> x.getNumber() == accountNumber).findFirst();
    }

    public void put(long accountNumber, int value) throws NoSuchClientAccountException {
        Optional<Account> acc = find(accountNumber);
        if (acc.isPresent()) {
            acc.get().put(value);
        } else {
            throw new NoSuchClientAccountException(accountNumber, number);
        }
    }

    public void take(long accountNumber, int value) throws NoSuchClientAccountException, InsufficientFundsException {
        Optional<Account> acc = find(accountNumber);
        if (acc.isPresent()) {
            acc.get().take(value);
        } else {
            throw new NoSuchClientAccountException(accountNumber, number);
        }
    }

    public void transfer(long senderNumber, long recipientNumber, int value) throws TransferException {
        Optional<Account> acc = find(senderNumber);
        try {
            if (acc.isPresent()) {
                acc.get().transfer(recipientNumber, value);
            } else {
                throw new NoSuchClientAccountException(senderNumber, number);
            }
        } catch (NoSuchClientAccountException | NoSuchAccountException | InsufficientFundsException e) {
            throw new TransferException(e);
        }

    }

    public void open() {
        Account acc = Account.open(number);
        accounts.add(acc);
    }

    public void close(long accountNumber) throws NoSuchClientAccountException, NoSuchAccountException {
        Optional<Account> acc = find(accountNumber);
        if (acc.isPresent()) {
            acc.get().close();
            accounts.remove(acc.get());
        } else {
            throw new NoSuchClientAccountException(accountNumber, number);
        }
    }

    public int getBalance(long accountNumber) throws NoSuchClientAccountException {
        Optional<Account> acc = find(accountNumber);
        if (acc.isPresent()) {
            return acc.get().getAmount();
        } else {
            throw new NoSuchClientAccountException(accountNumber, number);
        }
    }

    public int getBalance() {
        int balance = 0;
        for (var acc : accounts) {
            balance += acc.getAmount();
        }
        return balance;
    }
}
