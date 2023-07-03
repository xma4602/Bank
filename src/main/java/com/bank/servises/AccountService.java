package com.bank.servises;

import com.bank.NumberGenerator;
import com.bank.entities.Account;
import com.bank.entities.Client;
import com.bank.errors.NoSuchAccountException;
import com.bank.errors.NoSuchClientAccountException;
import com.bank.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account getAccount(long accountNumber) throws NoSuchAccountException {
        Optional<Account> result = accountRepository.findAccountByNumber(accountNumber);
        if (result.isEmpty()) {
            throw new NoSuchAccountException(accountNumber);
        }
        return result.get();
    }

    public Account getAccount(long clientNumber, long accountNumber) throws NoSuchClientAccountException {
        Optional<Account> result = accountRepository.findAccountByNumberAndClientNumber(clientNumber, accountNumber);
        if (result.isEmpty()) {
            throw new NoSuchClientAccountException(accountNumber, clientNumber);
        }
        return result.get();
    }

    public List<Account> getClientAccounts(long clientNumber) {
        return accountRepository.findAccountsByClientNumber(clientNumber);
    }

    public Account addAccountToClient(Client client) {
        Account account = new Account(NumberGenerator.get(), 0, client);
        accountRepository.save(account);
        return account;
    }
    public void createAccount(Account account) {
        accountRepository.save(account);
    }

    public void removeAccount(long accountNumber) throws NoSuchAccountException {
        Account account = getAccount(accountNumber);
        accountRepository.delete(account);
    }
    public void removeAccount(Account account) {
        accountRepository.delete(account);
    }


}
