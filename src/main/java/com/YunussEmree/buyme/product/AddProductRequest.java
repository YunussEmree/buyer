package com.YunussEmree.buyme.product;

import com.YunussEmree.buyme.category.Category;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AddProductRequest  {
    private Long id;
    private String name;
    private String description;
    private String brand;
    private BigDecimal price;
    private int inventory;
    private Category category;
}
