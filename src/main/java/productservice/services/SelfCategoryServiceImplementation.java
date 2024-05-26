package productservice.services;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import productservice.models.Category;
import productservice.models.Product;
import productservice.repositories.CategoryRepository;
import productservice.repositories.ProductRepository;
import productservice.services.interfaces.CategoryService;
import productservice.services.interfaces.ProductService;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class SelfCategoryServiceImplementation implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public SelfCategoryServiceImplementation(CategoryRepository categoryRepository,
                                             ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Optional<List<Category>> getAllCategories() {
        return Optional.of(this.categoryRepository.findAll());
    }

    @Override
    public Optional<List<Product>> getProductsInCategory(Category category) {
        return Optional.of(this.productRepository.findByCategoryName(category.getName()));
    }
}
