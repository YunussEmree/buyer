package com.yunussemree.buyer.cart;

import com.yunussemree.buyer.cartitem.CartItemDto;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
public class CartDto {
    private Long id;
    private BigDecimal totalAmount;
    private BigDecimal totalPrice;
    private Set<CartItemDto> items;
}
