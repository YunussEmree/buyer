package com.yunussemree.buyer.order;

import java.util.List;

public interface IOrderService {
    Order placeOrder(Long userId);
    Order getOrderById(Long orderId);
    void cancelOrder(Long orderId);
    List<Order> getUserOrders(Long userId);
}
