package productservice.services.interfaces;

import productservice.models.Category;
import productservice.models.Product;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();
    List<Product> getProductsInCategory(Category category);

}
