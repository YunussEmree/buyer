package com.yunussemree.buyer.order;

public interface IOrderService {
    Order placeOrder(Order order);
    Order getOrder(Long orderId);
    void cancelOrder(Long orderId);
}
