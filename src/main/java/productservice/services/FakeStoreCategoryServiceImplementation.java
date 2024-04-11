package productservice.services;

import org.springframework.stereotype.Service;
import productservice.services.interfaces.CategoryService;

@Service
public class FakeStoreCategoryServiceImplementation implements CategoryService {
    @Override
    public String getAllCategories() {
        return null;
    }

    @Override
    public String getProductsInCategory(Long categoryId) {
        return null;
    }
}
