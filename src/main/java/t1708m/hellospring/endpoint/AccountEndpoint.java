package t1708m.hellospring.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import t1708m.hellospring.entity.Account;
import t1708m.hellospring.entity.RESTPagination;
import t1708m.hellospring.entity.RESTResponse;
import t1708m.hellospring.service.AccountService;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/accounts")
public class AccountEndpoint {
    @Autowired
    private AccountService accountService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Object> list(
            @RequestParam(defaultValue = "1", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int limit) {
        Page<Account> accountPage = accountService.getList(page, limit);

        return new ResponseEntity<>(new RESTResponse.Success()
                .addDatas(accountPage.getContent())
                .setPagination(new RESTPagination(page, limit, accountPage.getTotalPages(), accountPage.getNumberOfElements()))
                .setMessage("Action success!")
                .setStatus(HttpStatus.OK.value())
                .buildDatas(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{email}")
    public ResponseEntity<Object> detail(@PathVariable String email) {
        Account account = accountService.getDetail(email);
        if (account == null) {
            return new ResponseEntity<>(new RESTResponse.SimpleError()
                    .setCode(HttpStatus.NOT_FOUND.value())
                    .setMessage("Không tìm thấy tài khoản.")
                    .build(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new RESTResponse.Success()
                .addData(account)
                .setMessage("Action success!")
                .setStatus(HttpStatus.OK.value())
                .build(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> store(@RequestBody Account account) {
        return new ResponseEntity<>(new RESTResponse.Success()
                .addData(accountService.register(account))
                .setMessage("Save success!")
                .setStatus(HttpStatus.CREATED.value())
                .build(), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{email}")
    public ResponseEntity<Object> update(@PathVariable String email, @RequestBody Account account) {
        return new ResponseEntity<>(new RESTResponse.Success()
                .addData(accountService.update(email, account))
                .setMessage("Save success!")
                .setStatus(HttpStatus.OK.value())
                .build(), HttpStatus.OK);
    }
}
