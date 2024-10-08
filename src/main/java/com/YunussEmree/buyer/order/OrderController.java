package com.yunussemree.buyer.order;

import com.yunussemree.buyer.controllers.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @GetMapping()
    public ResponseEntity<ApiResponse> getOrders() {
        return ResponseEntity.ok(new ApiResponse("Orders retrieved successfully"));
    }
}
