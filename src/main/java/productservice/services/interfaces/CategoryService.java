package productservice.services.interfaces;

public interface CategoryService {
    String getAllCategories();
    String getProductsInCategory(Long categoryId);

}
