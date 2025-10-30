package com.turkcell.payment_service.Domain.Model;

public class Payment {
    private final PaymentId paymentId;
    private final OrderId orderId;
    private Money money;
    private PaymentMethod paymentMethod;
    private PaymentStatus paymentStatus;

    private Payment(PaymentId paymentId, OrderId orderId, Money money, PaymentMethod paymentMethod, PaymentStatus paymentStatus) {
        this.paymentId = paymentId;
        this.orderId = orderId;
        this.money = money;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
    }
    private static Payment create(OrderId orderId, Money money, PaymentMethod paymentMethod, PaymentStatus paymentStatus){
        // burada validasyonları yazabiliriz
        return new Payment(PaymentId.generate(), orderId, money, paymentMethod, paymentStatus);
    }
    // rehydrate method ile db yazılmıs veriyi geri yüklemek
    private static Payment rehydrate(PaymentId paymentId, OrderId orderId, Money money, PaymentMethod paymentMethod, PaymentStatus paymentStatus){
       return new Payment(paymentId, orderId, money, paymentMethod, paymentStatus);
    }

}
