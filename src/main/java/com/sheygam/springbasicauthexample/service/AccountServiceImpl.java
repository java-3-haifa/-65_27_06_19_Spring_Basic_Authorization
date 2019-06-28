package com.sheygam.springbasicauthexample.service;

import com.sheygam.springbasicauthexample.repository.AccountRepository;
import com.sheygam.springbasicauthexample.repository.entity.AccountEntity;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    private AccountRepository repository;
    private TokenService validationService;

    public AccountServiceImpl(AccountRepository repository, TokenService validationService) {
        this.repository = repository;
        this.validationService = validationService;
    }

    @Override
    public boolean addUser(String firstName, String lastName, String token) {
        AccountCredentials account = validationService.decodeToken(token);
        return repository.addUser(firstName,lastName,account.email,account.password);
    }

    @Override
    public boolean removeUser(String email, String token) {
        AccountCredentials account = validationService.decodeToken(token);
        AccountEntity entity = repository.findUserByEmail(account.email);
        if(entity == null){
            throw new RuntimeException("Wrong admin credentials");
        }
        if(entity.role == AccountEntity.ROLE.ADMIN){
            return repository.removeUser(email);

        }
        throw new RuntimeException("Permission denied");
    }
}
