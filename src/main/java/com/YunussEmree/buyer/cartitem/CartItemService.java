package com.YunussEmree.buyer.cartitem;

import com.YunussEmree.buyer.cart.Cart;
import com.YunussEmree.buyer.cart.CartRepository;
import com.YunussEmree.buyer.cart.ICartService;
import com.YunussEmree.buyer.core.utilities.exceptions.ResourceNotFoundException;
import com.YunussEmree.buyer.product.IProductService;
import com.YunussEmree.buyer.product.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartItemService implements ICartItemService {

    private final CartRepository cartRepository;
    private CartItemRepository cartItemRepository;
    private IProductService iProductService;
    private ICartService iCartService;


    @Override
    public void addCartItem(Long cartId, Long productId, int quantity) {
        // Get cart
        // Get product
        // Check if product is already in cart
        // If it is, update quantity
        // If it is not, add new cart item
        Cart cart = iCartService.getCart(cartId);
        Product product = iProductService.getProductById(productId);
        CartItem cartItem = cart.getItems().stream().filter(item ->
                item.getProduct().getId().equals(productId))
                .findFirst().orElse(new CartItem());

        if (cartItem.getId() == null) {
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
            cartItem.setUnitPrice(product.getPrice());
        } else {
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        }
        cartItem.setTotalPrice();
        cart.addItem(cartItem);
        cartItemRepository.save(cartItem);
        cartRepository.save(cart);
    }

    @Override
    public void removeCartItem(Long cartId, Long productId) {
        Cart cart = iCartService.getCart(cartId);
        CartItem cartItem = cart.getItems().stream().filter(item ->
                item.getProduct().getId().equals(productId))
                .findFirst().orElseThrow(() -> new ResourceNotFoundException("Product not found in cart!"));
        cart.removeItem(cartItem);
        cartItemRepository.delete(cartItem);
        cartRepository.save(cart);
    }

    @Override
    public void updateCartItemQuantity(Long cartId, Long productId, int quantity) {
        Cart cart = iCartService.getCart(cartId);
        CartItem cartItem = cart.getItems().stream().filter(item ->
                item.getProduct().getId().equals(productId))
                .findFirst().orElseThrow(() -> new ResourceNotFoundException("Product not found in cart!"));
        cartItem.setQuantity(quantity);
        cartItem.setTotalPrice();
        cartItemRepository.save(cartItem);

    }
}
