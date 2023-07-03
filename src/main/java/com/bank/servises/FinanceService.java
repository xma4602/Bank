package com.bank.servises;

import com.bank.entities.Account;
import com.bank.errors.InsufficientFundsException;
import com.bank.errors.NoSuchAccountException;
import com.bank.errors.NoSuchClientAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class FinanceService {
    private final AccountService accountService;

    @Autowired
    public FinanceService(AccountService accountService) {
        this.accountService = accountService;
    }

    public long getAccountBalance(UUID clientId, UUID accountId) throws NoSuchClientAccountException {
        return accountService.getAccount(clientId, accountId).getAmount();
    }

    public long getAccountBalance(UUID clientId) throws NoSuchClientAccountException {
        var accounts = accountService.getClientAccounts(clientId);
        if (accounts.isEmpty()) throw new NoSuchClientAccountException(clientId);
        return accounts.stream().mapToLong(Account::getAmount).sum();
    }

    @Transactional
    public void putOn(UUID accountId, long sum) throws NoSuchAccountException {
        var account = accountService.getAccount(accountId);
        accountService.removeAccount(accountId);
        account.setAmount(account.getAmount() + sum);
        accountService.createAccount(account);
    }

    @Transactional
    public void takeFrom(UUID accountId, long sum) throws NoSuchAccountException, InsufficientFundsException {
        var account = accountService.getAccount(accountId);
        if (account.getAmount() < sum) throw new InsufficientFundsException(accountId, sum);

        accountService.removeAccount(accountId);
        account.setAmount(account.getAmount() -sum);
        accountService.createAccount(account);
    }

    @Transactional
    public void transfer(UUID senderAccountId, UUID recipientAccountId, long sum) throws NoSuchAccountException, InsufficientFundsException {
        var sender = accountService.getAccount(senderAccountId);
        var recipient = accountService.getAccount(recipientAccountId);
        if (sender.getAmount() < sum) throw new InsufficientFundsException(senderAccountId, sum);

        accountService.removeAccount(sender);
        sender.setAmount(sender.getAmount() - sum);
        accountService.createAccount(sender);

        accountService.removeAccount(recipient);
        sender.setAmount(sender.getAmount() + sum);
        accountService.createAccount(recipient);
    }
}
