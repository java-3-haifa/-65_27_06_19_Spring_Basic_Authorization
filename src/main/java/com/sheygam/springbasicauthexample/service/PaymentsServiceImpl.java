package com.sheygam.springbasicauthexample.service;

import com.sheygam.springbasicauthexample.controller.dto.PaymentDto;
import com.sheygam.springbasicauthexample.repository.PaymentRepository;
import com.sheygam.springbasicauthexample.repository.entity.PaymentEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentsServiceImpl implements PaymentService {
    private PaymentRepository repository;
    private TokenService tokenService;

    public PaymentsServiceImpl(PaymentRepository repository, TokenService tokenService) {
        this.repository = repository;
        this.tokenService = tokenService;
    }

    @Override
    public boolean addPayment(String token, String title, LocalDate date, double amount) {
        AccountCredentials credentials = tokenService.decodeToken(token);
        repository.addPayment(credentials.email,title,date,amount);
        return true;
    }

    @Override
    public List<PaymentDto> getAll(String token) {
        AccountCredentials credentials = tokenService.decodeToken(token);
        return repository.getAllPayments(credentials.email)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }



    @Override
    public boolean deleteAll(String email) {
        repository.removeAll(email);
        return true;
    }

    private PaymentDto mapToDto(PaymentEntity paymentEntity) {
        return new PaymentDto(paymentEntity.title,paymentEntity.date,paymentEntity.amount);
    }
}
