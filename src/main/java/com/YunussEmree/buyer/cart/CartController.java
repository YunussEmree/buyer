package com.YunussEmree.buyer.cart;

import com.YunussEmree.buyer.controllers.ApiResponse;
import com.YunussEmree.buyer.core.utilities.exceptions.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.net.http.HttpResponse;

@RestController
@RequestMapping("${api.prefix}/cart")
public class CartController {
    private final ICartService iCartService;

    public CartController(ICartService iCartService) {
        this.iCartService = iCartService;
    }

    @GetMapping("/{id}")
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

    @GetMapping("/clear/{id}")
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
