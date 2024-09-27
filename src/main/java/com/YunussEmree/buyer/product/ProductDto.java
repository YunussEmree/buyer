package com.YunussEmree.buyer.product;

import com.YunussEmree.buyer.category.Category;
import com.YunussEmree.buyer.image.ImageDto;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductDto {
    private Long id;
    private String name;
    private String brand;
    private BigDecimal price;
    private int inventory;
    private String description;
    private Category category;
    private List<ImageDto> images;
}
