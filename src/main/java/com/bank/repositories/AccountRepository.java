package com.bank.repositories;

import com.bank.entities.Account;
import com.bank.entities.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends CrudRepository<Account, UUID> {

    Optional<Account> findAccountByNumber(long number);

    Optional<Account> findAccountByNumberAndClientNumber(long accountNumber, long clientNumber);

    List<Account> findAccountsByClientNumber(long number);
}
