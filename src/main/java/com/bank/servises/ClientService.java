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
import java.util.Optional;
import java.util.UUID;


@Service
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client getClientById(UUID clientId) throws NoSuchClientException {
        Optional<Client> result = clientRepository.findClientById(clientId);
        if (result.isEmpty()) {
            throw new NoSuchClientException(clientId);
        }

        return result.get();
    }

    public List<Account> getClientAccounts(UUID clientId) throws NoSuchClientException {
        Optional<Client> result = clientRepository.findClientById(clientId);
        if (result.isEmpty()) {
            throw new NoSuchClientException(clientId);
        }

        return result.get().getAccounts();
    }

    public Client addClient(String name) {
        Client client = new Client(NumberGenerator.get(), name, new ArrayList<>(0));
        clientRepository.save(client);
        return client;
    }

    public void removeClient(UUID clientId) throws NoSuchClientException {
        Client client = getClientById(clientId);
        clientRepository.delete(client);
    }
}
