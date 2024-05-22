package productservice.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import productservice.dtos.FakeStoreCategoryResponseDto;
import productservice.dtos.FakeStoreProductResponseDto;
import productservice.models.Category;
import productservice.models.Product;
import productservice.services.interfaces.CategoryService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping()
    public ResponseEntity<FakeStoreCategoryResponseDto> getAllCategories() {
        List<Category> allCategories = this.categoryService.getAllCategories();
        FakeStoreCategoryResponseDto responseDto = new FakeStoreCategoryResponseDto();
        responseDto.setCategory(allCategories);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/{categoryName}")
    public ResponseEntity<List<FakeStoreProductResponseDto>> getSingleCategory(@PathVariable("categoryName") String categoryName){
        Category category = new Category();
        category.setName(categoryName);
        List<Product> productsInCategory = this.categoryService.getProductsInCategory(category);
        List<FakeStoreProductResponseDto> responseDtos = new ArrayList<>();
        for(Product product : productsInCategory){
            FakeStoreProductResponseDto dto = new FakeStoreProductResponseDto();
            dto.setProduct(product);
            responseDtos.add(dto);
        }
        return new ResponseEntity<>(responseDtos, HttpStatus.OK);
    }
}
