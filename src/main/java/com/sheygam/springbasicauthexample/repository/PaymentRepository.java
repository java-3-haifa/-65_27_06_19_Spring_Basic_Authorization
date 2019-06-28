package com.sheygam.springbasicauthexample.repository;

import com.sheygam.springbasicauthexample.repository.entity.PaymentEntity;

import java.time.LocalDate;
import java.util.List;

public interface PaymentRepository {
    void addPayment(String owner, String title, LocalDate date, double amount);
    List<PaymentEntity> getAllPayments(String ownerEmail);
    void removeAll(String ownerEmail);
}
