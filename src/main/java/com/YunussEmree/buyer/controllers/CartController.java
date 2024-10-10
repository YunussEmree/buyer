package com.yunussemree.buyer.controllers;

import com.yunussemree.buyer.cart.Cart;
import com.yunussemree.buyer.cart.ICartService;
import com.yunussemree.buyer.core.utilities.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("${api.prefix}/cart")
public class CartController {
    private final ICartService iCartService;

    @Autowired
    public CartController(ICartService iCartService) {
        this.iCartService = iCartService;
    }

    @GetMapping("/{id}/my-cart")
    public ResponseEntity<ApiResponse> getCart(@PathVariable Long id) {
        try {
            Cart cart = iCartService.getCartById(id);
            return ResponseEntity.ok(new ApiResponse("Cart retrieved successfully", cart));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.badRequest().body(new ApiResponse("Cart not found for get cart request!", null));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse("An error occurred when get cart request", null));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> clearCart(@PathVariable Long id) {
        try {
            iCartService.clearCart(id);
            return ResponseEntity.ok(new ApiResponse("Cart cleared successfully", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.badRequest().body(new ApiResponse("Cart not found for clear cart request!", null));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse("An error occurred when clear cart request", null));
        }
    }

    @GetMapping("/{cartId}/total-price")
    public ResponseEntity<ApiResponse> getTotalPrice(@PathVariable Long cartId) {
        try {
            BigDecimal totalPrice = iCartService.getTotalPrice(cartId);
            return ResponseEntity.ok(new ApiResponse("Total price retrieved successfully", totalPrice));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.badRequest().body(new ApiResponse("Cart not found for get total price request!", null));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse("An error occurred when get total price request", null));
        }
    }






}
