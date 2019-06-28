package com.sheygam.springbasicauthexample.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class PaymentDto {
    public String title;
    @JsonFormat(pattern = "dd/MM/yyyy")
    public LocalDate date;
    public double amount;

    public PaymentDto(String title, LocalDate date, double amount) {
        this.title = title;
        this.date = date;
        this.amount = amount;
    }

    public PaymentDto() {
    }
}
