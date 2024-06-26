package productservice.services.interfaces;

import productservice.models.Category;
import productservice.models.Product;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Optional<List<Category>> getAllCategories();
    Optional<List<Product>>getProductsInCategory(Category category);

}
