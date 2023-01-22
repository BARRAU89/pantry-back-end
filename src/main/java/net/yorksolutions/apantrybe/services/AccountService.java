package net.yorksolutions.apantrybe.services;
import net.yorksolutions.apantrybe.models.Account;
import net.yorksolutions.apantrybe.repository.AccountRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository){

        this.accountRepository = accountRepository;
    }

    public Account getByUsernameAndPassword(String username, String password) {
        return accountRepository.findAccountByUsernameAndPassword(username, password)
                .orElse(null);
    }
    public Account getAccountById(Long id) {

        return accountRepository.findById(id).orElse(null);
    }
    public void register(Account accountRequest) throws Exception {
        if (accountRepository.findAccountByUsername(accountRequest.username).isPresent())
            throw new Exception();

        if(accountRequest.username.equals(null)){
            throw new Exception();
        } else if(accountRequest.password.equals(null)){
            throw new Exception();
        }
        accountRepository.save(accountRequest);
    }

    public void deleteById(Long id) throws Exception{

        Optional<Account> accountOpt = accountRepository.findById(id);
        accountRepository.deleteById(id);

    }

    public void editAccount(Long id, Account accountRequest) throws Exception {
        Optional<Account> accountOpt = this.accountRepository.findById(id);


        Account account = accountOpt.get();
        account.username = accountRequest.username;
        account.password = accountRequest.password;

        if(accountRequest.username.equals(null))
            throw new Exception();

        accountRepository.save(account);

    }

    public Iterable<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

}