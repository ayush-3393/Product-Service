package productservice.services.interfaces;

import productservice.models.Product;

import java.util.List;

public interface ProductService {
    Product getSingleProduct(Long productId);
    List<Product> getAllProducts();
    Product addNewProduct(Product product);
    Product updateProduct(Product product);
    Boolean deleteProduct(Long productId);
}
