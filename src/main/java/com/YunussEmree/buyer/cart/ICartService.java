package com.yunussemree.buyer.cart;

import com.yunussemree.buyer.user.User;

import java.math.BigDecimal;

public interface ICartService {
    Cart getCartByUserId(Long id);
    Cart getCartById(Long id);
    void clearCart(Long id);
    BigDecimal getTotalPrice(Long cartId);
    Cart createCart(User user);
}
