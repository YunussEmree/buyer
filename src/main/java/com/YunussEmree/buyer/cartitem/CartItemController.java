package com.YunussEmree.buyer.cartitem;

import com.YunussEmree.buyer.cart.ICartService;
import com.YunussEmree.buyer.controllers.ApiResponse;
import com.YunussEmree.buyer.core.utilities.exceptions.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}/cart-item")
public class CartItemController {
    private final ICartItemService iCartItemService;
    private final ICartService iCartService;

    public CartItemController(ICartItemService iCartItemService, ICartService iCartService) {
        this.iCartItemService = iCartItemService;
        this.iCartService = iCartService;

    }

    @PostMapping
    public ResponseEntity<ApiResponse> addCartItem(@RequestParam Long cartId, @RequestParam Long productId, @RequestParam int quantity) {
        try{
            if(cartId == null){
                cartId = iCartService.createCart();
            }


            iCartItemService.addCartItem(cartId, productId, quantity);
            return ResponseEntity.ok(new ApiResponse("Cart item added successfully", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.badRequest().body(new ApiResponse("Cart not found!", null));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse("An error occurred", null));
        }
    }

    @DeleteMapping("{cartId}/{productId}")
    public ResponseEntity<ApiResponse> removeCartItem(@PathVariable Long cartId, @PathVariable Long productId) {
        try{
            iCartItemService.removeCartItem(cartId, productId);
            return ResponseEntity.ok(new ApiResponse("Cart item removed successfully", null));
        }catch (ResourceNotFoundException e) {
            return ResponseEntity.badRequest().body(new ApiResponse("Cart not found!", null));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse("An error occurred", null));
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse> updateCartItemQuantity(@RequestParam Long cartId, @RequestParam Long productId, @RequestParam int quantity) {
        try{
            iCartItemService.updateCartItemQuantity(cartId, productId, quantity);
            return ResponseEntity.ok(new ApiResponse("Cart item updated successfully", null));
        }catch (ResourceNotFoundException e) {
            return ResponseEntity.badRequest().body(new ApiResponse("Cart not found!", null));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse("An error occurred", null));
        }
    }


}
