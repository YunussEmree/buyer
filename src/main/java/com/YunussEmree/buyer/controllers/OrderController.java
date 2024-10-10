package com.yunussemree.buyer.controllers;

import com.yunussemree.buyer.core.utilities.exceptions.ResourceNotFoundException;
import com.yunussemree.buyer.order.IOrderService;
import com.yunussemree.buyer.order.Order;
import com.yunussemree.buyer.order.OrderDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/orders")
@RestController
public class OrderController {

    private final IOrderService iOrderService;

    public OrderController(IOrderService iOrderService) {
        this.iOrderService = iOrderService;
    }

    @GetMapping("/{userId}/all")
    public ResponseEntity<ApiResponse> getOrders(@PathVariable Long userId) {
        try{
            List<OrderDto> orders = iOrderService.getUserOrders(userId);
            return ResponseEntity.ok(new ApiResponse("Get all orders request success!", orders));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<ApiResponse> getOrder(@PathVariable Long orderId) {
        try{
            OrderDto order = iOrderService.getOrderById(orderId);
            return ResponseEntity.ok(new ApiResponse("Get order request success!", order));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<ApiResponse> cancelOrder(@PathVariable Long orderId) {
        try{
            iOrderService.cancelOrder(orderId);
            return ResponseEntity.ok(new ApiResponse("Order cancelled successfully!", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PostMapping("/{userId}")
    public ResponseEntity<ApiResponse> placeOrder(@PathVariable Long userId) {
        try{
            Order order = iOrderService.placeOrder(userId);
            return ResponseEntity.ok(new ApiResponse("Order placed successfully!", order));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("An error occured", e.getMessage()));
        }
    }

}
