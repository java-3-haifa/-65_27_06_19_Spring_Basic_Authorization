package com.sheygam.springbasicauthexample.repository;

import com.sheygam.springbasicauthexample.repository.AccountRepository;
import com.sheygam.springbasicauthexample.repository.entity.AccountEntity;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentHashMap;

@Repository
public class AccountRepositoryImpl implements AccountRepository {
    private ConcurrentHashMap<String, AccountEntity> users;

    public AccountRepositoryImpl() {
        users = new ConcurrentHashMap<>();
        AccountEntity account = new AccountEntity();
        account.email = "admin@mail.com";
        account.password = "Aa12345~";
        account.firstName = "Admin";
        account.lastName = "Admin";
        account.role = AccountEntity.ROLE.ADMIN;
        users.put(account.email,account);
    }

    @Override
    public boolean addUser(String firstName, String lastName, String email, String password) {
        AccountEntity account = new AccountEntity();
        account.email = email;
        account.password = password;
        account.firstName = firstName;
        account.lastName = lastName;
        account.role = AccountEntity.ROLE.USER;
        if(users.putIfAbsent(email,account) == null){
            return true;
        }
        throw new RuntimeException("User already exist");
    }

    @Override
    public boolean removeUser(String email) {
        if(users.remove(email) == null){
            throw new RuntimeException("User with email: " + email + " does not exist");
        }
        return true;
    }

    @Override
    public AccountEntity findUserByEmail(String email) {
        return users.get(email);
    }
}
