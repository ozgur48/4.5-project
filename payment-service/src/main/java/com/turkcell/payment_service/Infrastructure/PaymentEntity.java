package com.turkcell.payment_service.Infrastructure;

import com.turkcell.payment_service.Domain.Model.PaymentMethod;
import com.turkcell.payment_service.Domain.Model.PaymentStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name="payments")
public class PaymentEntity {
    @Id
    @Column(nullable = false, updatable = false)
    private String id;

    //@ManyToOne
    //@JoinColumn(name = "order_id", nullable = false)
    //private OrderEntity orderId;

    private BigDecimal amount;
    private String currency;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus paymentStatus;



}
