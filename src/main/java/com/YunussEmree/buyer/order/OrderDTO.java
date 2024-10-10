package com.yunussemree.buyer.order;

import com.yunussemree.buyer.orderitem.OrderItemDto;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class OrderDto {
    private Long orderId;
    private Long userId;
    private String orderStatus;
    private LocalDate orderDate;
    private BigDecimal totalAmount;
    private BigDecimal totalPrice;
    private List<OrderItemDto> items;


}
