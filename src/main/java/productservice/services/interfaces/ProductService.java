package productservice.services.interfaces;

import productservice.dtos.FakeStoreProductRequestDto;
import productservice.models.Product;

import java.util.List;

public interface ProductService {
    Product getSingleProduct(Long productId);
    List<Product> getAllProducts();
    Product addNewProduct(Product product);
    Product updateProduct(Long productId, Product product);
    Product deleteProduct(Long productId);
}
