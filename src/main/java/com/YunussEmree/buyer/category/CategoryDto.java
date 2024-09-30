package com.YunussEmree.buyer.category;

import com.YunussEmree.buyer.product.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public record CategoryDto(Long id, String name, @JsonIgnore List<Product> products) {
}
