package com.sheygam.springbasicauthexample.service;

import com.sheygam.springbasicauthexample.controller.dto.PaymentDto;

import java.time.LocalDate;
import java.util.List;

public interface PaymentService {
    boolean addPayment(String token,String title, LocalDate date, double amount);
    List<PaymentDto> getAll(String token);
    boolean deleteAll(String email);
}
