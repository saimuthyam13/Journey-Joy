package com.journeyjoy.service;

import com.journeyjoy.model.Payment;
import java.util.List;

public interface PaymentService {
    Payment savePayment(Payment payment);
    List<Payment> getAllPayments();
}
