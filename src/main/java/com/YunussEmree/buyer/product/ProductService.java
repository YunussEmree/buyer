package com.YunussEmree.buyer.product;

import com.YunussEmree.buyer.category.Category;
import com.YunussEmree.buyer.category.CategoryRepository;
import com.YunussEmree.buyer.core.utilities.exceptions.ResourceAlreadyExistsException;
import com.YunussEmree.buyer.core.utilities.exceptions.ResourceNotFoundException;
import com.YunussEmree.buyer.core.utilities.mappers.IModelMapperManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor // Generates a constructor with required arguments
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final IModelMapperManager iModelMapperManager;


    @Override
    public void addProduct(Product product) {
        //check the category, is it found in DB?
        // If yes, set it as the new product category
        // If no, then save it as a new category
        //Then set a new product
        Category category = categoryRepository.findByName(product.getCategory().getName());

        Optional.of(category).ifPresentOrElse(product::setCategory, () -> {
            Category newCategory = new Category();
            newCategory.setName(product.getCategory().getName());
            product.setCategory(categoryRepository.save(newCategory));
        });

        Optional.of(product).filter(p -> !productRepository.existsByName(p.getName()))
                .map(productRepository::save)
                .orElseThrow(() -> new ResourceAlreadyExistsException("Product already exists!"));
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found!"));
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.findById(id)
                .ifPresentOrElse(productRepository::delete, () -> {
                    throw new ResourceNotFoundException("Product not found!");
                });
    }

    @Override
    public void updateProduct(Product product, Long id) {
        Optional.ofNullable(getProductById(id)).map(oldProduct -> {
            oldProduct.setName(oldProduct.getName());
            return productRepository.save(oldProduct);
        }).orElseThrow(() -> new ResourceNotFoundException("Product not found!"));
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategoryName(category);
    }

    @Override
    public List<Product> getProductsByBrand(String brand) {
        return productRepository.findByBrand(brand);
    }

    @Override
    public List<Product> getProductsByCategoryAndBrand(String category, String brand) {
        return productRepository.findByCategoryNameAndBrand(category, brand);
    }

    @Override
    public List<Product> getProductsByBrandAndName(String brand, String name) {
        return productRepository.findByBrandAndName(brand, name);
    }

    @Override
    public Long countProductsByBrandAndName(String brand, String name) {
        return productRepository.countByBrandAndName(brand, name);
    }
}
