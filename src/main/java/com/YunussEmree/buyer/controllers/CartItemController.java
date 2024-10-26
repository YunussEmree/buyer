package com.yunussemree.buyer.controllers;

import com.yunussemree.buyer.cart.Cart;
import com.yunussemree.buyer.cart.CartService;
import com.yunussemree.buyer.cart.ICartService;
import com.yunussemree.buyer.cartitem.ICartItemService;
import com.yunussemree.buyer.core.utilities.exceptions.ResourceNotFoundException;
import com.yunussemree.buyer.user.IUserService;
import com.yunussemree.buyer.user.User;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}/cart-items")
public class CartItemController {
    private final ICartItemService cartItemService;
    private final IUserService userService;
    private final ICartService cartService;

    @Autowired
    public CartItemController(ICartItemService cartItemService, IUserService userService, CartService cartService) {
        this.cartItemService = cartItemService;
        this.userService = userService;
        this.cartService = cartService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addCartItem(@RequestParam Long productId, @RequestParam int quantity) {
        try{
            User user = userService.getAuthenticatedUser();
            Cart cart = cartService.createCart(user);
            cartItemService.addCartItem(cart.getId(), productId, quantity);
            return ResponseEntity.ok(new ApiResponse("Cart item added successfully", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.badRequest().body(new ApiResponse("Cart not found for add cart item request!", null));
        } catch (JwtException e) {
            return ResponseEntity.status(401).body(new ApiResponse(e.getMessage(), null));
        }

        catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse("An error occured when add cart item request", null));
        }
    }

    @DeleteMapping("{cartId}/{productId}")
    public ResponseEntity<ApiResponse> removeCartItem(@PathVariable Long cartId, @PathVariable Long productId) {
        try{
            cartItemService.removeCartItem(cartId, productId);
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
            cartItemService.updateCartItemQuantity(cartId, productId, quantity);
            return ResponseEntity.ok(new ApiResponse("Cart item updated successfully", null));
        }catch (ResourceNotFoundException e) {
            return ResponseEntity.badRequest().body(new ApiResponse("Cart not found for update cart item quantity request!", null));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse("An error occurred when update cart item quantity request", null));
        }
    }


}
