package com.yunussemree.buyer.cart;

import java.math.BigDecimal;

public interface ICartService {
    Cart getCartByUserId(Long id);
    Cart getCartById(Long id);
    void clearCart(Long id);
    BigDecimal getTotalPrice(Long cartId);
    Long createCart();
}
