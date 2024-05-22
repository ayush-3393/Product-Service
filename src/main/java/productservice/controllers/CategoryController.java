package productservice.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import productservice.dtos.FakeStoreCategoryResponseDto;
import productservice.dtos.FakeStoreProductResponseDto;
import productservice.exceptions.NotFoundException;
import productservice.models.Category;
import productservice.models.Product;
import productservice.services.interfaces.CategoryService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping()
    public ResponseEntity<FakeStoreCategoryResponseDto> getAllCategories() throws NotFoundException {
        Optional<List<Category>> allCategories = this.categoryService.getAllCategories();
        if (allCategories.isEmpty()){
            throw new NotFoundException("No categories found");
        }
        FakeStoreCategoryResponseDto responseDto = new FakeStoreCategoryResponseDto();
        responseDto.setCategory(allCategories.get());
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/{categoryName}")
    public ResponseEntity<List<FakeStoreProductResponseDto>> getSingleCategory(@PathVariable("categoryName") String categoryName) throws NotFoundException {
        Category category = new Category();
        category.setName(categoryName);
        Optional<List<Product>> productsInCategory = this.categoryService.getProductsInCategory(category);
        if (productsInCategory.isEmpty()){
            throw new NotFoundException("No products found in category");
        }
        List<FakeStoreProductResponseDto> responseDtos = new ArrayList<>();
        for(Product product : productsInCategory.get()){
            FakeStoreProductResponseDto dto = new FakeStoreProductResponseDto();
            dto.setProduct(product);
            responseDtos.add(dto);
        }
        return new ResponseEntity<>(responseDtos, HttpStatus.OK);
    }
}
