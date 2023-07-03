package com.bank.servises;

import com.bank.entities.Account;
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

    public void saveAccount(Account account) {
        accountRepository.save(account);
    }

    public void removeAccount(UUID accountId) {
        accountRepository.deleteAccountById(accountId);
    }

    public void updateAccountAmount(UUID accountId, long value){
        accountRepository.updateById(accountId, value);
    }
}
