package t1708m.hellospring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import t1708m.hellospring.entity.Account;
import t1708m.hellospring.service.AccountService;

import javax.validation.Valid;
import java.util.ArrayList;

@Controller
@RequestMapping(value = "/accounts")
public class AccountController {

    @Autowired
    AccountService accountService;

    @RequestMapping(method = RequestMethod.GET, value = "/login")
    public String showLoginPage() {
        return "login";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public String showListAccount(Model model) {
        model.addAttribute("list", accountService.getList(1, 10));
        return "list";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/create")
    public String createAccount(Model model) {
        model.addAttribute("account", new Account());
        return "form";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public String storeAccount(@Valid Account account, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "form";
        }
        accountService.register(account);
        return "success";
    }
}
