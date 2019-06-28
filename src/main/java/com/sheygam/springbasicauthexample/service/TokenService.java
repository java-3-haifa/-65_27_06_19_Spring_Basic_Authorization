package com.sheygam.springbasicauthexample.service;

public interface TokenService {
    AccountCredentials decodeToken(String token);
}
