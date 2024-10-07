package com.yunussemree.buyer.product;

import java.util.List;

public interface IProductService {
    Product addProduct(Product product);

    void deleteProductById(Long id);

    Product updateProduct(Product product, Long productId);

    Product getProductById(Long id);

    List<Product> getAllProducts();

    List<Product> getProductsByCategory(String category);

    List<Product> getProductsByBrand(String brand);

    List<Product> getProductsByCategoryAndBrand(String category, String brand);

    List<Product> getProductsByBrandAndName(String brand, String name);

    Long countProductsByBrandAndName(String brand, String name);

    ProductDto convertToDto(Product product);

    List<ProductDto> getConvertedProducts(List<Product> products);
}
