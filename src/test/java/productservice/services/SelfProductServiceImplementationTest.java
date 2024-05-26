package productservice.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.annotation.Commit;
import productservice.exceptions.NotFoundException;
import productservice.models.Category;
import productservice.models.Product;
import productservice.models.Rating;
import productservice.repositories.ProductRepository;
import productservice.services.interfaces.ProductService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class SelfProductServiceImplementationTest {

    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @Test
    void getSingleProductByIdShouldReturnASingleProduct() {
        Product product = new Product();
        product.setId(1L);
        product.setTitle("Product 1");
        product.setPrice(100.0);
        product.setDescription("Product 1 description");
        Category category = new Category();
        category.setName("Category 1");
        category.setId(1L);
        category.setDescription("Category 1 description");
        product.setCategory(category);
        Rating rating = new Rating();
        rating.setRate(4.5);
        rating.setCount(100);
        product.setRating(rating);
        Product savedProduct = new Product();

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Optional<Product> returnedProduct = productService.getSingleProduct(1L);

        assertTrue(returnedProduct.isPresent());
        assertEquals(product.getId(), returnedProduct.get().getId());
        assertEquals(product.getTitle(), returnedProduct.get().getTitle());
        assertEquals(product.getPrice(), returnedProduct.get().getPrice());
        assertEquals(product.getDescription(), returnedProduct.get().getDescription());
    }

    @Test
    void getSingleProductByIdShouldReturnEmptyOptional(){
        when(productRepository.findById(1L)).thenReturn(Optional.empty());
        Optional<Product> foundProduct = productService.getSingleProduct(1L);
        assertTrue(foundProduct.isEmpty());
    }

    @Test
    void getAllProductsShouldReturnAllProducts(){
        Product product1 = new Product();
        product1.setId(1L);
        product1.setTitle("Product 1");
        product1.setPrice(100.0);
        product1.setDescription("Product 1 description");
        Category category1 = new Category();
        category1.setName("Category 1");
        category1.setId(1L);
        category1.setDescription("Category 1 description");
        product1.setCategory(category1);
        Rating rating1 = new Rating();
        rating1.setRate(4.5);
        rating1.setCount(100);
        product1.setRating(rating1);

        Product product2 = new Product();
        product2.setId(2L);
        product2.setTitle("Product 2");
        product2.setPrice(200.0);
        product2.setDescription("Product 2 description");
        Category category2 = new Category();
        category2.setName("Category 2");
        category2.setId(2L);
        category2.setDescription("Category 2 description");
        product2.setCategory(category2);
        Rating rating2 = new Rating();
        rating2.setRate(4.0);
        rating2.setCount(200);
        product2.setRating(rating2);

        when(productRepository.findAll()).thenReturn(java.util.List.of(product1, product2));

        Optional<java.util.List<Product>> allProducts = productService.getAllProducts();

        assertTrue(allProducts.isPresent());
        assertEquals(2, allProducts.get().size());
        assertEquals(product1.getId(), allProducts.get().get(0).getId());
        assertEquals(product1.getTitle(), allProducts.get().get(0).getTitle());
        assertEquals(product1.getPrice(), allProducts.get().get(0).getPrice());
        assertEquals(product1.getDescription(), allProducts.get().get(0).getDescription());
        assertEquals(product2.getId(), allProducts.get().get(1).getId());
        assertEquals(product2.getTitle(), allProducts.get().get(1).getTitle());
        assertEquals(product2.getPrice(), allProducts.get().get(1).getPrice());
        assertEquals(product2.getDescription(), allProducts.get().get(1).getDescription());
    }

    @Test
    void addNewProductShouldReturnNewProduct(){
        Product product = new Product();
        product.setId(1L);
        product.setTitle("Product 1");
        product.setPrice(100.0);
        product.setDescription("Product 1 description");
        Category category = new Category();
        category.setName("Category 1");
        category.setId(1L);
        category.setDescription("Category 1 description");
        product.setCategory(category);
        Rating rating = new Rating();
        rating.setRate(4.5);
        rating.setCount(100);
        product.setRating(rating);

        when(productRepository.save(product)).thenReturn(product);

        Optional<Product> savedProduct = productService.addNewProduct(product);

        assertTrue(savedProduct.isPresent());
        assertEquals(product.getId(), savedProduct.get().getId());
        assertEquals(product.getTitle(), savedProduct.get().getTitle());
        assertEquals(product.getPrice(), savedProduct.get().getPrice());
        assertEquals(product.getDescription(), savedProduct.get().getDescription());
    }

    @Test
    void updateProductShouldReturnUpdatedProduct(){
        Product product = new Product();
        product.setId(1L);
        product.setTitle("Product 1");
        product.setPrice(100.0);
        product.setDescription("Product 1 description");
        Category category = new Category();
        category.setName("Category 1");
        category.setId(1L);
        category.setDescription("Category 1 description");
        product.setCategory(category);
        Rating rating = new Rating();
        rating.setRate(4.5);
        rating.setCount(100);
        product.setRating(rating);

        Product updatedProduct = new Product();
        updatedProduct.setId(1L);
        updatedProduct.setTitle("Product 1 updated");
        updatedProduct.setPrice(200.0);
        updatedProduct.setDescription("Product 1 description updated");
        Category updatedCategory = new Category();
        updatedCategory.setName("Category 1 updated");
        updatedCategory.setId(1L);
        updatedCategory.setDescription("Category 1 description updated");
        updatedProduct.setCategory(updatedCategory);
        Rating updatedRating = new Rating();
        updatedRating.setRate(4.0);
        updatedRating.setCount(200);
        updatedProduct.setRating(updatedRating);

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productRepository.save(product)).thenReturn(updatedProduct);

        Optional<Product> updatedProductOptional = productService.updateProduct(1L, updatedProduct);

        assertTrue(updatedProductOptional.isPresent());
        assertEquals(updatedProduct.getId(), updatedProductOptional.get().getId());
        assertEquals(updatedProduct.getTitle(), updatedProductOptional.get().getTitle());
        assertEquals(updatedProduct.getPrice(), updatedProductOptional.get().getPrice());
        assertEquals(updatedProduct.getDescription(), updatedProductOptional.get().getDescription());
    }

    @Test
    void deleteProductShouldReturnDeletedProduct(){
        Product product = new Product();
        product.setId(1L);
        product.setTitle("Product 1");
        product.setPrice(100.0);
        product.setDescription("Product 1 description");
        Category category = new Category();
        category.setName("Category 1");
        category.setId(1L);
        category.setDescription("Category 1 description");
        product.setCategory(category);
        Rating rating = new Rating();
        rating.setRate(4.5);
        rating.setCount(100);
        product.setRating(rating);

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productRepository.save(product)).thenReturn(product);

        Optional<Product> deletedProduct = productService.deleteProduct(1L);

        assertTrue(deletedProduct.isPresent());
        assertTrue(deletedProduct.get().getIsDeleted());
    }
}