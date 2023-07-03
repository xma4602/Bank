package com.bank.servises;

import com.bank.entities.Account;
import com.bank.entities.Client;
import com.bank.errors.NoSuchAccountException;
import com.bank.errors.NoSuchClientAccountException;
import com.bank.errors.NoSuchClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Account openAccount(long clientNumber) throws NoSuchClientException {
        Client client = clientService.getClientByNumber(clientNumber);
        return accountService.addAccountToClient(client);
    }

    public void closeAccount(long clientNumber, long accountNumber) throws NoSuchClientAccountException, NoSuchAccountException {
        Account account = accountService.getAccount(clientNumber, accountNumber);
        accountService.removeAccount(accountNumber);
    }


}
