package com.bank.servises;

import com.bank.NumberGenerator;
import com.bank.entities.Account;
import com.bank.entities.Client;
import com.bank.errors.NoSuchClientAccountException;
import com.bank.errors.NoSuchClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService extends FinanceService {
    private final AccountService accountService;
    private final ClientService clientService;

    @Autowired
    public UserService(AccountService accountService, ClientService clientService) {
        super(accountService);
        this.accountService = accountService;
        this.clientService = clientService;
    }

    public Account openAccount(UUID clientId) throws NoSuchClientException {
        Client client = clientService.getClientById(clientId);
        Account account = new Account(NumberGenerator.get(), 0, client);
        accountService.saveAccount(account);
        return account;
    }

    public void closeAccount(UUID clientId, UUID accountId) throws NoSuchClientAccountException {
        Account account = accountService.getAccount(clientId, accountId);
        accountService.removeAccount(accountId);
    }


}
