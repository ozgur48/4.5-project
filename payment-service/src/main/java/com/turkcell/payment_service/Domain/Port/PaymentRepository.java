package com.turkcell.payment_service.Domain.Port;

import com.turkcell.payment_service.Domain.Model.Payment;
import com.turkcell.payment_service.Domain.Model.PaymentId;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository {
    Payment save(Payment payment);
    Optional<Payment> findById(PaymentId paymentId);
    List<Payment> findAll();
    List<Payment> findAllPaged(Integer pageIndex, Integer pageSize);
    void delete(PaymentId paymentId);
}
