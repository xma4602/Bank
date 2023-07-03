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
import java.util.UUID;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account getAccount(UUID accountId) throws NoSuchAccountException {
        Optional<Account> result = accountRepository.findAccountById(accountId);
        if (result.isEmpty()) {
            throw new NoSuchAccountException(accountId);
        }
        return result.get();
    }

    public Account getAccount(UUID clientId, UUID accountId) throws NoSuchClientAccountException {
        Optional<Account> result = accountRepository.findAccountByIdAndClientId(clientId, accountId);
        if (result.isEmpty()) {
            throw new NoSuchClientAccountException(accountId, clientId);
        }
        return result.get();
    }

    public List<Account> getClientAccounts(UUID clientId) {
        return accountRepository.findAccountsByClientId(clientId);
    }

    public Account addAccountToClient(Client client) {
        Account account = new Account(NumberGenerator.get(), 0, client);
        accountRepository.save(account);
        return account;
    }
    public void createAccount(Account account) {
        accountRepository.save(account);
    }

    public void removeAccount(UUID accountId) throws NoSuchAccountException {
        Account account = getAccount(accountId);
        accountRepository.delete(account);
    }
    public void removeAccount(Account account) {
        accountRepository.delete(account);
    }


}
