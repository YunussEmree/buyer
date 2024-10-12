package com.yunussemree.buyer.cart;

import com.yunussemree.buyer.core.utilities.exceptions.ResourceNotFoundException;
import com.yunussemree.buyer.user.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService implements ICartService{

    private CartRepository cartRepository;

    @Autowired
    public CartService(CartRepository cartRepository){
        this.cartRepository = cartRepository;
    }


    @Override
    public Cart getCartById(Long id) {
        Cart cart = cartRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cart not found when get cart service!"));
        BigDecimal totalAmount = cart.getTotalAmount();
        cart.setTotalAmount(totalAmount);
        return cartRepository.save(cart);
    }

    @Override
    public Cart getCartByUserId(Long userId) {
        return cartRepository.findByUserId(userId);
    }


    @Transactional // This annotation is used to ensure that the transaction is completed successfully.
    @Override
    public void clearCart(Long id) {
        Cart cart = cartRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cart not found when clear cart service!"));
        cart.getItems().clear();
        cart.setTotalAmount(BigDecimal.ZERO);
        cart.setTotalPrice(BigDecimal.ZERO);
        cartRepository.save(cart);
    }

    @Override
    public BigDecimal getTotalPrice(Long cartId) {
        return cartRepository.findById(cartId).orElseThrow(() -> new ResourceNotFoundException("Cart not found!")).getTotalPrice();
    }

    @Override
    public Cart createCart(User user) {
        return Optional.ofNullable(getCartByUserId(user.getId()))
                .orElseGet(() -> {
                    Cart cart = new Cart();
                    cart.setUser(user);
                    return cartRepository.save(cart);
                });
    }




}
