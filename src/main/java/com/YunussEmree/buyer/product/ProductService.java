package com.yunussemree.buyer.product;

import com.yunussemree.buyer.category.Category;
import com.yunussemree.buyer.category.CategoryRepository;
import com.yunussemree.buyer.core.utilities.exceptions.ResourceAlreadyExistsException;
import com.yunussemree.buyer.core.utilities.exceptions.ResourceNotFoundException;
import com.yunussemree.buyer.image.Image;
import com.yunussemree.buyer.image.ImageDto;
import com.yunussemree.buyer.image.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor // Generates a constructor with required arguments
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    private final ImageRepository imageRepository;


    @Override
    public Product addProduct(Product product) {
        //check the category, is it found in DB?
        // If yes, set it as the new product category
        // If no, then save it as a new category
        //Then set a new product



        Optional.ofNullable(categoryRepository.findByName(product.getCategory().getName())).ifPresentOrElse(product::setCategory, () -> {
            Category newCategory = new Category();
            newCategory.setName(product.getCategory().getName());
            product.setCategory(categoryRepository.save(newCategory));
        });

        Optional.of(product).filter(p -> !productRepository.existsByName(p.getName()))
                .map(productRepository::save)
                .orElseThrow(() -> new ResourceAlreadyExistsException("Product already exists when add product by id service!"));
        return product;
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found when get product by id service!"));
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.findById(id)
                .ifPresentOrElse(productRepository::delete, () -> {
                    throw new ResourceNotFoundException("Product not found when delete product by id service!");
                });
    }

    @Override
    public Product updateProduct(Product product, Long id) {
        Optional.ofNullable(getProductById(id)).map(oldProduct -> {
            oldProduct.setName(oldProduct.getName());
            return productRepository.save(oldProduct);
        }).orElseThrow(() -> new ResourceNotFoundException("Product not found when update product service!"));
        return product;
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


    @Override
    public List<ProductDto> getConvertedProducts(List<Product> products) {
        return products.stream().map(this::convertToDto).toList();
    }

    @Override
    public ProductDto convertToDto(Product product) {
        ProductDto productDto = modelMapper.map(product, ProductDto.class);
        List<Image> images = imageRepository.findByProductId(product.getId());
        List<ImageDto> imageDtos = images.stream().map(image -> modelMapper.map(image, ImageDto.class)).toList();
        productDto.setImages(imageDtos);
        return productDto;
    }
}
