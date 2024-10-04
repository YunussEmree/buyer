package com.YunussEmree.buyer.cart;

import com.YunussEmree.buyer.cartitem.CartItemRepository;
import com.YunussEmree.buyer.core.utilities.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicLong;

@Service
@RequiredArgsConstructor
public class CartService implements ICartService{

    private CartRepository cartRepository;
    private CartItemRepository cartItemRepository;
    private AtomicLong cartIdGenerator = new AtomicLong(0);


    @Override
    public Cart getCart(Long id) {
        Cart cart = cartRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cart not found!"));
        BigDecimal totalAmount = cart.getTotalAmount();
        cart.setTotalAmount(totalAmount);
        return cartRepository.save(cart);
    }

    @Override
    public void clearCart(Long id) {
        Cart cart = cartRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cart not found!"));
        cart.getItems().clear();
        cart.setTotalAmount(BigDecimal.ZERO);
        cart.setTotalPrice(BigDecimal.ZERO);
        cartRepository.save(cart);
    }

    @Override
    public BigDecimal getTotalPrice(Long cartId) {
        return cartRepository.findById(cartId).orElseThrow(() -> new ResourceNotFoundException("Cart not found!")).getTotalPrice();
    }


    public Long createCart() {
        Cart cart = new Cart();
        cart.setId(cartIdGenerator.incrementAndGet());
        return cartRepository.save(cart).getId();
    }


}
