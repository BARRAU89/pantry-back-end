package net.yorksolutions.apantrybe.repository;

import net.yorksolutions.apantrybe.models.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

    public Optional<Account> findAccountByUsernameAndPassword(String username, String password);
    public Optional<Account> findAccountByUsername(String username);
}