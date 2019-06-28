package com.sheygam.springbasicauthexample.repository;

import com.sheygam.springbasicauthexample.repository.entity.AccountEntity;

public interface AccountRepository {
    boolean addUser(String firstName, String lastName, String email, String password);
    boolean removeUser(String email);
    AccountEntity findUserByEmail(String email);
}
