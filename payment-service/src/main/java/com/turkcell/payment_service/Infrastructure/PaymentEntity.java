package com.turkcell.payment_service.Infrastructure;

import com.turkcell.payment_service.Domain.Model.PaymentMethod;
import com.turkcell.payment_service.Domain.Model.PaymentStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

@Entity
@Table(name="payments")
public class PaymentEntity {
    @Id
    @Column(nullable = false, updatable = false)
    private String id;

    //@OneToMany
    //@JoinColumn(name = "order_id", nullable = false)
    //private OrderId orderId;

    @Column(nullable = false)
    @Positive
    private BigDecimal amount;

    @Column(nullable = false)
    @NotBlank
    private String currency;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus paymentStatus;



}
