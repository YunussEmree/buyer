package com.YunussEmree.buyer.controllers;

import com.YunussEmree.buyer.core.utilities.exceptions.ResourceAlreadyExistsException;
import com.YunussEmree.buyer.core.utilities.exceptions.ResourceNotFoundException;
import com.YunussEmree.buyer.product.IProductService;
import com.YunussEmree.buyer.product.Product;
import com.YunussEmree.buyer.product.ProductDto;
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
            return ResponseEntity.ok(new ApiResponse("Success", convertedProducts));
        } catch (ResourceNotFoundException e){
            return ResponseEntity.status(404).body(new ApiResponse("Failed", null));
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getProductById(@PathVariable Long id) {
        try{
            Product product = productService.getProductById(id);
            ProductDto productDto = productService.convertToDto(product);
            return ResponseEntity.ok(new ApiResponse("Success", productDto));
        } catch (ResourceNotFoundException e){
            return ResponseEntity.status(404).body(new ApiResponse("Failed", null));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createProduct(@RequestBody Product product) {
        try{
            Product theProduct = productService.addProduct(product); //TODO Will be used DTO
            return ResponseEntity.ok(new ApiResponse("Success", theProduct));
        } catch (ResourceAlreadyExistsException e){
            return ResponseEntity.status(409).body(new ApiResponse("Failed", null));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        try{
            productService.updateProduct(product, id);
            return ResponseEntity.ok(new ApiResponse("Success", product));
        } catch (ResourceNotFoundException e){
            return ResponseEntity.status(404).body(new ApiResponse("Failed", null));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long id) {
        try{
            productService.deleteProductById(id);
            return ResponseEntity.ok(new ApiResponse("Success", null));
        } catch (ResourceNotFoundException e){
            return ResponseEntity.status(404).body(new ApiResponse("Failed", null));
        }
    }
}
