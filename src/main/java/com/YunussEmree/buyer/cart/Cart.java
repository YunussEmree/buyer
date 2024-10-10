package com.yunussemree.buyer.cart;

import com.yunussemree.buyer.cartitem.CartItem;
import com.yunussemree.buyer.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
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
    private Set<CartItem> items = new HashSet<>();

    public void addItem(CartItem item) {
        this.items.add(item);
        item.setCart(this);
        this.totalPrice = this.totalPrice.add(item.getTotalPrice());
        this.totalAmount = this.totalAmount.add(new BigDecimal(item.getQuantity()));
    }

    public void removeItem(CartItem item) {
        this.items.remove(item);
        item.setCart(null);
        this.totalPrice = this.totalPrice.subtract(item.getTotalPrice());
        this.totalAmount = this.totalAmount.subtract(new BigDecimal(item.getQuantity()));
    }

    public BigDecimal calculateTotalPrice() {
        BigDecimal newTotalPrice = this.getItems()
                .stream().map(CartItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        setTotalPrice(newTotalPrice);
        return newTotalPrice;
    }

    public BigDecimal calculateTotalAmount() {
        BigDecimal newTotalAmount = BigDecimal.valueOf(this.getItems()
                .stream().map(CartItem::getQuantity)
                .reduce(0, Integer::sum));
        setTotalAmount(newTotalAmount);
        return newTotalAmount;
    }

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;








}
