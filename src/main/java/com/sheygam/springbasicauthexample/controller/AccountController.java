package com.sheygam.springbasicauthexample.controller;

import com.sheygam.springbasicauthexample.controller.dto.UserRegDto;
import com.sheygam.springbasicauthexample.service.AccountService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("account")
public class AccountController {
    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping()
    public void registration(@RequestBody UserRegDto body,
                             @RequestHeader("Authorization") String token){
        accountService.addUser(body.firstName,body.lastName,token);
    }

    @DeleteMapping("{email}")
    public void deleteAccount(@PathVariable("email")String email,
                              @RequestHeader("Authorization")String token){
        accountService.removeUser(email,token);
    }
}
