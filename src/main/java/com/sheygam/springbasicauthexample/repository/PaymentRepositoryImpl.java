package com.sheygam.springbasicauthexample.repository;

import com.sheygam.springbasicauthexample.repository.entity.PaymentEntity;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Repository
public class PaymentRepositoryImpl implements PaymentRepository {
    private ConcurrentHashMap<String, CopyOnWriteArrayList<PaymentEntity>> payments = new ConcurrentHashMap<>();
    @Override
    public void addPayment(String owner, String title, LocalDate date, double amount) {
        PaymentEntity entity = new PaymentEntity(title,date,amount);
        payments.computeIfAbsent(owner,key -> new CopyOnWriteArrayList<>()).add(entity);
    }

    @Override
    public List<PaymentEntity> getAllPayments(String ownerEmail) {
        return payments.getOrDefault(ownerEmail,new CopyOnWriteArrayList<>());
    }

    @Override
    public void removeAll(String ownerEmail) {
        payments.remove(ownerEmail);
    }
}
