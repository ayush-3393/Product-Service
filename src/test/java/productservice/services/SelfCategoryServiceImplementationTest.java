package productservice.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import productservice.models.Category;
import productservice.models.Product;
import productservice.repositories.CategoryRepository;
import productservice.repositories.ProductRepository;
import productservice.services.interfaces.CategoryService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class SelfCategoryServiceImplementationTest {

    @Autowired
    private CategoryService categoryService;

    @MockBean
    private CategoryRepository categoryRepository;

    @MockBean
    private ProductRepository productRepository;

    @Test
    void getAllCategoriesShouldReturnAllTheCategories() {
        Category category1 = new Category();
        category1.setId(1L);
        category1.setName("Category 1");
        category1.setDescription("Category 1 description");

        Category category2 = new Category();
        category2.setId(2L);
        category2.setName("Category 2");
        category2.setDescription("Category 2 description");

        when(categoryRepository.findAll()).thenReturn(List.of(category1, category2));

        List<Category> categories = categoryService.getAllCategories().get();

        assertEquals(2, categories.size());
        assertEquals(category1.getId(), categories.get(0).getId());
        assertEquals(category1.getName(), categories.get(0).getName());
        assertEquals(category1.getDescription(), categories.get(0).getDescription());

        assertEquals(category2.getId(), categories.get(1).getId());
        assertEquals(category2.getName(), categories.get(1).getName());
        assertEquals(category2.getDescription(), categories.get(1).getDescription());
    }

    @Test
    void getAllProductsInACategoryShouldReturnAllTheProductsByCategoryName(){
        Category category = new Category();
        category.setName("Category 1");
        category.setDescription("Category 1 description");

        Product product1 = new Product();
        product1.setId(1L);
        product1.setTitle("Product 1");
        product1.setPrice(100.0);
        product1.setDescription("Product 1 description");
        product1.setImageUrl("Product 1 image");
        product1.setCategory(category);

        Product product2 = new Product();
        product2.setId(2L);
        product2.setTitle("Product 2");
        product2.setPrice(200.0);
        product2.setDescription("Product 2 description");
        product2.setImageUrl("Product 2 image");
        product2.setCategory(category);

        when(productRepository.findByCategoryName(category.getName())).thenReturn(List.of(product1, product2));

        List<Product> productList = categoryService.getProductsInCategory(category).get();

        assertEquals(2, productList.size());
        assertEquals(product1.getId(), productList.get(0).getId());
        assertEquals(product2.getId(), productList.get(1).getId());
    }
}