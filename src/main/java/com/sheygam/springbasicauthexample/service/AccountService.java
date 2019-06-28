package com.sheygam.springbasicauthexample.service;

public interface AccountService {
    boolean addUser(String firstName, String lastName, String token);
    boolean removeUser(String email, String token);
}
