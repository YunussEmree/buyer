package com.YunussEmree.buyer.product;

import com.YunussEmree.buyer.category.Category;
import com.YunussEmree.buyer.image.ImageDto;

import java.math.BigDecimal;
import java.util.List;

public record ProductDto(Long id, String name, String brand, BigDecimal price, int inventory, String description, Category category, List<ImageDto> images) {}
