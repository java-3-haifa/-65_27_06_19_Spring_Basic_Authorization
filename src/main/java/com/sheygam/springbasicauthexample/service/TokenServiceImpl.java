package com.sheygam.springbasicauthexample.service;

import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class TokenServiceImpl implements TokenService {

    @Override
    public AccountCredentials decodeToken(String token) {
        int index = token.indexOf(" ");
        token = token.substring(index + 1);
        byte[] base64DecodeBytes = Base64.getDecoder().decode(token);
        token = new String(base64DecodeBytes);
        String[] auth = token.split(":");
        return new AccountCredentials(auth[0], auth[1]);
    }
}

