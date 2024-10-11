package com.yunussemree.buyer.cartitem;

import com.yunussemree.buyer.product.Product;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartItemDto {
    private Long id;
    private Integer quantity;
    private Product product;
    private BigDecimal unitPrice;

}
