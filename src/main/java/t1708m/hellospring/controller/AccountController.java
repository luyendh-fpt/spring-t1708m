package t1708m.hellospring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import t1708m.hellospring.entity.Account;
import t1708m.hellospring.service.AccountService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Account>> list() {
        return new ResponseEntity<>(accountService.getList(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{email}")
    public ResponseEntity<Account> detail(@PathVariable String email) {
        Account account = accountService.getDetail(email);
        if (account == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Account> store(@RequestBody Account account) {
        return new ResponseEntity<>(accountService.register(account), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{email}")
    public ResponseEntity<Account> update(@PathVariable String email, @RequestBody Account account) {
        return new ResponseEntity<>(accountService.update(email, account), HttpStatus.OK);
    }
}
