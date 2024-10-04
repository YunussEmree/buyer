package com.YunussEmree.buyer.controllers;

import com.YunussEmree.buyer.cart.Cart;
import com.YunussEmree.buyer.cart.ICartService;
import com.YunussEmree.buyer.core.utilities.exceptions.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("${api.prefix}/cart")
public class CartController {
    private final ICartService iCartService;

    public CartController(ICartService iCartService) {
        this.iCartService = iCartService;
    }

    @GetMapping("/{id}/my-cart")
    public ResponseEntity<ApiResponse> getCart(@PathVariable Long id) {
        try {
            Cart cart = iCartService.getCart(id);
            return ResponseEntity.ok(new ApiResponse("Cart retrieved successfully", cart));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.badRequest().body(new ApiResponse("Cart not found!", null));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse("An error occurred", null));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> clearCart(@PathVariable Long id) {
        try {
            iCartService.clearCart(id);
            return ResponseEntity.ok(new ApiResponse("Cart cleared successfully", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.badRequest().body(new ApiResponse("Cart not found!", null));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse("An error occurred", null));
        }
    }

    @GetMapping("/totalPrice/{cartId}")
    public ResponseEntity<ApiResponse> getTotalPrice(@PathVariable Long cartId) {
        try {
            BigDecimal totalPrice = iCartService.getTotalPrice(cartId);
            return ResponseEntity.ok(new ApiResponse("Total price retrieved successfully", totalPrice));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.badRequest().body(new ApiResponse("Cart not found!", null));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse("An error occurred", null));
        }
    }






}
