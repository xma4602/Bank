package com.bank.servises;

import com.bank.NumberGenerator;
import com.bank.entities.Account;
import com.bank.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public List<Account> getClientAccounts(UUID id) {
        return accountRepository.findAccountsByClientID(id);
    }

    public Account addAccount(UUID client_id) {
        Account account = new Account(client_id, NumberGenerator.get(), 0);
        accountRepository.save(account);
        return account;
    }

    public boolean removeAccount(long accountNumber) {
        var result = accountRepository.findAccountByNumber(accountNumber);
        if (result.isEmpty()) return false;

        accountRepository.delete(result.get());
        return true;
    }

}