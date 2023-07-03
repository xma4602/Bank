package com.bank.repositories;

import com.bank.entities.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends CrudRepository<Account, UUID> {
    Optional<Account> findAccountById(UUID id);

    Optional<Account> findAccountByIdAndClientId(UUID accountId, UUID clientId);

    List<Account> findAccountsByClientId(UUID id);

    void deleteAccountById(UUID id);

    long updateById(UUID id, long amount);

    long updateById(UUID id, String name);
}
