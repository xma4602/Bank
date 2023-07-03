package com.bank.servises;

import com.bank.entities.Account;
import com.bank.errors.InsufficientFundsException;
import com.bank.errors.NoSuchAccountException;
import com.bank.errors.NoSuchClientAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FinanceService {
    private final AccountService accountService;

    @Autowired
    public FinanceService(AccountService accountService) {
        this.accountService = accountService;
    }

    public long getAccountBalance(long clientNumber, long accountNumber) throws NoSuchClientAccountException {
        return accountService.getAccount(clientNumber, accountNumber).getAmount();
    }

    public long getAccountBalance(long clientNumber) throws NoSuchClientAccountException {
        var accounts = accountService.getClientAccounts(clientNumber);
        if (accounts.isEmpty()) throw new NoSuchClientAccountException(clientNumber);
        return accounts.stream().mapToLong(Account::getAmount).sum();
    }

    @Transactional
    public void putOn(long accountNumber, long sum) throws NoSuchAccountException {
        var account = accountService.getAccount(accountNumber);
        accountService.removeAccount(accountNumber);
        account.setAmount(account.getAmount() + sum);
        accountService.createAccount(account);
    }

    @Transactional
    public void takeFrom(long accountNumber, long sum) throws NoSuchAccountException, InsufficientFundsException {
        var account = accountService.getAccount(accountNumber);
        if (account.getAmount() < sum) throw new InsufficientFundsException(accountNumber, sum);

        accountService.removeAccount(accountNumber);
        account.setAmount(account.getAmount() -sum);
        accountService.createAccount(account);
    }

    @Transactional
    public void transfer(long senderAccountNumber, long recipientAccountNumber, long sum) throws NoSuchAccountException, InsufficientFundsException {
        var sender = accountService.getAccount(senderAccountNumber);
        var recipient = accountService.getAccount(recipientAccountNumber);
        if (sender.getAmount() < sum) throw new InsufficientFundsException(senderAccountNumber, sum);

        accountService.removeAccount(sender);
        sender.setAmount(sender.getAmount() - sum);
        accountService.createAccount(sender);

        accountService.removeAccount(recipient);
        sender.setAmount(sender.getAmount() + sum);
        accountService.createAccount(recipient);
    }
}
