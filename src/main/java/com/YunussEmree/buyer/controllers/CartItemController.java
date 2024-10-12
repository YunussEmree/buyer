package com.yunussemree.buyer.controllers;

import com.yunussemree.buyer.cart.Cart;
import com.yunussemree.buyer.cart.CartService;
import com.yunussemree.buyer.cartitem.ICartItemService;
import com.yunussemree.buyer.core.utilities.exceptions.ResourceNotFoundException;
import com.yunussemree.buyer.user.IUserService;
import com.yunussemree.buyer.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}/cart-item")
public class CartItemController {
    private final ICartItemService iCartItemService;
    private final IUserService iUserService;
    private final CartService cartService;

    @Autowired
    public CartItemController(ICartItemService iCartItemService, IUserService iUserService, CartService cartService) {
        this.iCartItemService = iCartItemService;
        this.iUserService = iUserService;
        this.cartService = cartService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addCartItem(@RequestParam Long productId, @RequestParam int quantity) {
        try{
            User user = iUserService.getUserById(1L);
            Cart cart = cartService.createCart(user);
            iCartItemService.addCartItem(cart.getId(), productId, quantity);
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

    @PatchMapping
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
