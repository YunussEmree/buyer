package com.yunussemree.buyer.controllers;

import com.yunussemree.buyer.core.utilities.exceptions.ResourceAlreadyExistsException;
import com.yunussemree.buyer.core.utilities.exceptions.ResourceNotFoundException;
import com.yunussemree.buyer.product.AddProductRequest;
import com.yunussemree.buyer.product.IProductService;
import com.yunussemree.buyer.product.Product;
import com.yunussemree.buyer.product.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/products")
public class ProductController {
    IProductService productService;

    @Autowired
    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getProducts() {
        try{
            List<Product> products = productService.getAllProducts();
            List<ProductDto> convertedProducts = productService.getConvertedProducts(products);
            return ResponseEntity.ok(new ApiResponse("Success for get products request", convertedProducts));
        } catch (ResourceNotFoundException e){
            return ResponseEntity.status(404).body(new ApiResponse("Resource not found when get products request", null));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getProductById(@PathVariable Long id) {
        try{
            Product product = productService.getProductById(id);
            ProductDto productDto = productService.convertToDto(product);
            return ResponseEntity.ok(new ApiResponse("Success when get product by id request", productDto));
        } catch (ResourceNotFoundException e){
            return ResponseEntity.status(404).body(new ApiResponse("Resource not found when get product by id request", null));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createProduct(@RequestBody AddProductRequest request) {
        try{
            Product theProduct = productService.addProduct(request); //TODO Will be used DTO
            return ResponseEntity.ok(new ApiResponse("Success for create product request", theProduct));
        } catch (ResourceAlreadyExistsException e){
            return ResponseEntity.status(409).body(new ApiResponse("Resource already exists when create product request", null));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        try{
            productService.updateProduct(product, id);
            return ResponseEntity.ok(new ApiResponse("Success for update product request", product));
        } catch (ResourceNotFoundException e){
            return ResponseEntity.status(404).body(new ApiResponse("Resource not found in ", null));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long id) {
        try{
            productService.deleteProductById(id);
            return ResponseEntity.ok(new ApiResponse("Success for delete product request", null));
        } catch (ResourceNotFoundException e){
            return ResponseEntity.status(404).body(new ApiResponse("Failed", null));
        }
    }
}
