package productservice.services;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import productservice.exceptions.NotFoundException;
import productservice.models.Category;
import productservice.models.Product;
import productservice.models.Rating;
import productservice.repositories.ProductRepository;
import productservice.services.interfaces.ProductService;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class SelfProductServiceImplementation implements ProductService {

    private final ProductRepository productRepository;

    public SelfProductServiceImplementation(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Optional<Product> getSingleProduct(Long productId) {
        return this.productRepository.findById(productId);
    }

    @Override
    public Optional<List<Product>> getAllProducts() {
        return Optional.of(this.productRepository.findAll());
    }

    @Override
    public Optional<Product> addNewProduct(Product product) {
        return Optional.of(this.productRepository.save(product));
    }

    @Override
    public Optional<Product> updateProduct(Long productId, Product product) {
        Optional<Product> existingProduct = this.productRepository.findById(productId);
        if (existingProduct.isEmpty()) {
            return Optional.empty();
        }
        Product productFromDb = existingProduct.get();

        productFromDb.setTitle(product.getTitle());
        productFromDb.setPrice(product.getPrice());
        productFromDb.setDescription(product.getDescription());
        productFromDb.setCategory(product.getCategory());
        productFromDb.setImageUrl(product.getImageUrl());
        productFromDb.setRating(product.getRating());
        return Optional.of(this.productRepository.save(productFromDb));
    }

    @Override
    public Optional<Product> deleteProduct(Long productId) {
        Optional<Product> existingProduct = this.productRepository.findById(productId);
        if (existingProduct.isEmpty()) {
            return Optional.empty();
        }
        Product productFromDb = existingProduct.get();
        productFromDb.setIsDeleted(true);
        return Optional.of(this.productRepository.save(productFromDb));
    }
}
