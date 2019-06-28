package com.sheygam.springbasicauthexample.controller;

import com.sheygam.springbasicauthexample.controller.dto.PaymentDto;
import com.sheygam.springbasicauthexample.service.PaymentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("payment")
public class PaymentController {

    private PaymentService service;

    public PaymentController(PaymentService service) {
        this.service = service;
    }

    @PostMapping()
    public void addPayment(@RequestBody PaymentDto body, @RequestHeader("Authorization") String token){
        service.addPayment(token,body.title,body.date,body.amount);
    }

    @GetMapping
    public List<PaymentDto> getAllPayments(@RequestHeader("Authorization") String token){
        return service.getAll(token);
    }

    @DeleteMapping("/all/{email}")
    public void deleteAll(@PathVariable("email")String email){
        service.deleteAll(email);
    }
}
