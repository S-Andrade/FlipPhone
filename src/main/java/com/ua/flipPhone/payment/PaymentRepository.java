package com.ua.flipPhone.payment;


import org.springframework.data.repository.CrudRepository;

import com.ua.flipPhone.payment.Payment;


public interface PaymentRepository extends CrudRepository<Payment, Integer> {

}