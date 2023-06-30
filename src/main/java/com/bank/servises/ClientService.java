package com.bank.servises;

import com.bank.NumberGenerator;
import com.bank.entities.Account;
import com.bank.entities.Client;
import com.bank.errors.NoSuchClientException;
import com.bank.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client getClientByNumber(long clientNumber) throws NoSuchClientException {
        var result = clientRepository.findClientByNumber(clientNumber);
        if (result.isEmpty()) throw new NoSuchClientException(clientNumber);

        return result.get();
    }

    public List<Account> getClientAccounts(long clientNumber) throws NoSuchClientException {
        var result = clientRepository.findClientByNumber(clientNumber);
        if (result.isEmpty()) throw new NoSuchClientException(clientNumber);

        return result.get().getAccounts();
    }

    public Client addClient(String name) {
        Client client = new Client(NumberGenerator.get(), name, new ArrayList<>(0));
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
}
