package com.yunussemree.buyer.order;

import java.util.List;

public interface IOrderService {
    Order placeOrder(Long userId);
    OrderDto getOrderById(Long orderId);
    void cancelOrder(Long orderId);
    List<OrderDto> getUserOrders(Long userId);
}
