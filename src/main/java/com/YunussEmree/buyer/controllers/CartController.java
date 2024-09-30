package com.YunussEmree.buyer.controllers;

import com.YunussEmree.buyer.cart.ICartService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.prefix}/cart")
public class CartController {
    private final ICartService iCartService;

    public CartController(ICartService iCartService) {
        this.iCartService = iCartService;
    }
}
