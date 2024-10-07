package com.yunussemree.buyer.controllers;

import com.yunussemree.buyer.cart.ICartService;
import com.yunussemree.buyer.cartitem.ICartItemService;
import com.yunussemree.buyer.core.utilities.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}/cart-item")
public class CartItemController {
    private final ICartItemService iCartItemService;
    private final ICartService iCartService;

    @Autowired
    public CartItemController(ICartItemService iCartItemService, ICartService iCartService) {
        this.iCartItemService = iCartItemService;
        this.iCartService = iCartService;

    }

    @PostMapping
    public ResponseEntity<ApiResponse> addCartItem(@RequestParam(required = false) Long cartId, @RequestParam Long productId, @RequestParam int quantity) {
        try{
            if(cartId == null){
                cartId = iCartService.createCart();
            }
            iCartItemService.addCartItem(cartId, productId, quantity);
            return ResponseEntity.ok(new ApiResponse("Cart item added successfully", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.badRequest().body(new ApiResponse("Cart not found for add cart item request!", null));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse("An error occured when add cart item request", null));
        }
    }

    @DeleteMapping("{cartId}/{productId}")
    public ResponseEntity<ApiResponse> removeCartItem(@PathVariable Long cartId, @PathVariable Long productId) {
        try{
            iCartItemService.removeCartItem(cartId, productId);
            return ResponseEntity.ok(new ApiResponse("Cart item removed successfully", null));
        }catch (ResourceNotFoundException e) {
            return ResponseEntity.badRequest().body(new ApiResponse("Cart not found for remove cart item request!", null));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse("An error occurred when remove cart item request", null));
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse> updateCartItemQuantity(@RequestParam Long cartId, @RequestParam Long productId, @RequestParam int quantity) {
        try{
            iCartItemService.updateCartItemQuantity(cartId, productId, quantity);
            return ResponseEntity.ok(new ApiResponse("Cart item updated successfully", null));
        }catch (ResourceNotFoundException e) {
            return ResponseEntity.badRequest().body(new ApiResponse("Cart not found for update cart item quantity request!", null));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse("An error occurred when update cart item quantity request", null));
        }
    }


}
