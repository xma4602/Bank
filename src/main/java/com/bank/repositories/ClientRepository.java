package com.bank.repositories;

import com.bank.entities.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClientRepository extends CrudRepository<Client, UUID> {

    Optional<Client> queryClientById(UUID id);

    Optional<Client> queryClientByNumber(long number);

    List<Client> queryClientsByName(String name);
}
