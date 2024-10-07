package com.yunussemree.buyer.cartitem;

public interface ICartItemService {

    void addCartItem(Long cartId, Long productId, int quantity);
    void removeCartItem(Long cartId, Long productId);
    void updateCartItemQuantity(Long cartId, Long productId, int quantity);
}
