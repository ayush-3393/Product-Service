package productservice.services.interfaces;

import productservice.dtos.FakeStoreProductRequestDto;
import productservice.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Optional<Product> getSingleProduct(Long productId);
    Optional<List<Product>> getAllProducts();
    Optional<Product> addNewProduct(Product product);
    Optional<Product> updateProduct(Long productId, Product product);
    Optional<Product> deleteProduct(Long productId);
}
