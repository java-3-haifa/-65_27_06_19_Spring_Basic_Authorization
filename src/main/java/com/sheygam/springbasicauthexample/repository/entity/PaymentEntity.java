package com.sheygam.springbasicauthexample.repository.entity;

import java.time.LocalDate;

public class PaymentEntity {
    public String title;
    public LocalDate date;
    public double amount;

    public PaymentEntity() {
    }

    public PaymentEntity(String title, LocalDate date, double amount) {
        this.title = title;
        this.date = date;
        this.amount = amount;
    }
}
