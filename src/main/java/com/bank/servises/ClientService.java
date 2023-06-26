package com.bank.servises;

import com.bank.NumberGenerator;
import com.bank.entities.Account;
import com.bank.errors.NoSuchClientAccountException;
import com.bank.errors.NoSuchClientException;
import com.bank.entities.Client;
import com.bank.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AccountService accountService;

    public Client getClientByNumber(long clientNumber) throws NoSuchClientException {
        var result = clientRepository.queryClientByNumber(clientNumber);
        if (result.isEmpty()) throw new NoSuchClientException(clientNumber);

        var client = result.get();
        client.setAccounts(accountService.getClientAccounts(client.getId()));
        return client;
    }

    public Client addClient(String name) {
        Client client = new Client(NumberGenerator.get(), name, new ArrayList<>());
        clientRepository.save(client);
        return client;
    }

    public boolean removeClient(long clientNumber) {
        try {
            var client = getClientByNumber(clientNumber);
            clientRepository.delete(client);
            return true;
        } catch (NoSuchClientException e) {
            return false;
        }
    }

    public Account openAccount(long clientNumber) throws NoSuchClientException {
        var result = clientRepository.queryClientByNumber(clientNumber);
        if (result.isEmpty()) throw new NoSuchClientException(clientNumber);

        Client client = result.get();
        return accountService.addAccount(client.getId());
    }

    public void closeAccount(long clientNumber, long accountNumber) throws NoSuchClientException, NoSuchClientAccountException {
        var result = clientRepository.queryClientByNumber(clientNumber);
        if (result.isEmpty()) throw new NoSuchClientException(clientNumber);
        Client client = result.get();
        if (client.getAccounts().stream().noneMatch(x -> x.getNumber() == accountNumber))
            throw new NoSuchClientAccountException(accountNumber, clientNumber);
    }
}
