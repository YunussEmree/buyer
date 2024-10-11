package com.yunussemree.buyer.user;

import com.yunussemree.buyer.cart.CartDto;
import com.yunussemree.buyer.order.Order;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private List<Order> orders;
    private CartDto cart;
}
