package com.sheygam.springbasicauthexample.repository.entity;

public class AccountEntity {
    public enum ROLE{
        USER,ADMIN
    }
    public String email;
    public String password;
    public ROLE role;
    public String firstName;
    public String lastName;
}
