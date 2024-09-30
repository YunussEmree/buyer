package com.YunussEmree.buyer.category;

import com.YunussEmree.buyer.product.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class CategoryDto {  //Todo change to record
    private Long id;
    private String name;
    @JsonIgnore
    private List<Product> products;
}
