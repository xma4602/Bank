package com.bank.servises;

import com.bank.entities.Account;
import com.bank.errors.InsufficientFundsException;
import com.bank.errors.NoSuchAccountException;
import com.bank.errors.NoSuchClientAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class FinanceService {
    private final AccountService accountService;

    @Autowired
    public FinanceService(AccountService accountService) {
        this.accountService = accountService;
    }

    public long getAccountBalance(UUID accountId) throws NoSuchAccountException {
        return accountService.getAccount(accountId).getAmount();
    }

    public long getClientBalance(UUID clientId) throws NoSuchClientAccountException {
        List<Account> accounts = accountService.getClientAccounts(clientId);
        if (accounts.isEmpty()) {
            throw new NoSuchClientAccountException(clientId);
        }
        return accounts.stream().mapToLong(Account::getAmount).sum();
    }

    @Transactional
    public void putOn(UUID accountId, long sum) throws NoSuchAccountException {
        long balance = getAccountBalance(accountId);
        accountService.updateAccountAmount(accountId, balance + sum);
    }

    @Transactional
    public void takeFrom(UUID accountId, long sum) throws NoSuchAccountException, InsufficientFundsException {
        long balance = getAccountBalance(accountId);
        if (balance < sum) {
            throw new InsufficientFundsException(accountId, sum);
        }
        accountService.updateAccountAmount(accountId, balance - sum);
    }

    @Transactional
    public void transfer(UUID senderAccountId, UUID recipientAccountId, long sum) throws NoSuchAccountException, InsufficientFundsException {
        long senderBalance = getAccountBalance(senderAccountId);
        long recipientBalance = getAccountBalance(recipientAccountId);
        if (senderBalance < sum) {
            throw new InsufficientFundsException(senderAccountId, sum);
        }
        accountService.updateAccountAmount(senderAccountId, senderBalance - sum);
        accountService.updateAccountAmount(recipientAccountId, recipientBalance + sum);
    }
}
