package com.YunussEmree.buyer.cart;

import com.YunussEmree.buyer.cartitem.CartItem;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal totalPrice = new BigDecimal(0);
    private BigDecimal totalAmount = BigDecimal.ZERO;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CartItem> items;

    public void addItem(CartItem item) {
        items.add(item);
        item.setCart(this);
        this.totalPrice = this.totalPrice.add(item.getTotalPrice());
        this.totalAmount = this.totalAmount.add(new BigDecimal(item.getQuantity()));
    }

    public void removeItem(CartItem item) {
        items.remove(item);
        item.setCart(null);
        this.totalPrice = this.totalPrice.subtract(item.getTotalPrice());
        this.totalAmount = this.totalAmount.subtract(new BigDecimal(item.getQuantity()));
    }



}