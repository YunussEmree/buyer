package com.yunussemree.buyer.product;

import com.yunussemree.buyer.category.Category;
import com.yunussemree.buyer.image.ImageDto;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
public class ProductDto{ //Todo change to record
    private Long id;
    private String name;
    private String brand;
    private BigDecimal price;
    private int inventory;
    private String description;
    private Category category;
    private List<ImageDto> images;
}