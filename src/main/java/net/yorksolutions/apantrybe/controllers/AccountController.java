package net.yorksolutions.apantrybe.controllers;

import net.yorksolutions.apantrybe.models.Account;
import net.yorksolutions.apantrybe.services.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/accounts")
@CrossOrigin
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public ResponseEntity<Account> getByUsernameAndPassword(@RequestParam String username,
                                                            @RequestParam String password){

        final var account = accountService.getByUsernameAndPassword(username, password);

        if (account == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        return new ResponseEntity<>(account, HttpStatus.OK);
    }
    @GetMapping("/")
    public Iterable<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }
    @GetMapping("/{id}")
    public Account getAccountById(@PathVariable Long id) {
        return accountService.getAccountById(id);
    }

    @PostMapping
    public void register(@RequestBody Account accountRequest) {
        try {
            accountService.register(accountRequest);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteAccountById(@PathVariable Long id) {
        try {
            accountService.deleteById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public void editAccount(@PathVariable Long id, @RequestBody Account accountRequest){
        try {
            accountService.editAccount(id, accountRequest);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

}
