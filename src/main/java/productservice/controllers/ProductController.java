package productservice.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import productservice.dtos.FakeStoreProductResponseDto;
import productservice.dtos.FakeStoreProductRequestDto;
import productservice.models.Category;
import productservice.models.Product;
import productservice.services.interfaces.ProductService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{productId}")
    public ResponseEntity<FakeStoreProductResponseDto> getSingleProduct(@PathVariable("productId") Long productId){
        Product product = this.productService.getSingleProduct(productId);
        FakeStoreProductResponseDto responseDto = new FakeStoreProductResponseDto();
        responseDto.setProduct(product);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<FakeStoreProductResponseDto>> getAllProducts(){
        List<Product> allProducts = this.productService.getAllProducts();
        List<FakeStoreProductResponseDto> responseDtos = new ArrayList<>();
        for(Product product : allProducts){
            FakeStoreProductResponseDto dto = new FakeStoreProductResponseDto();
            dto.setProduct(product);
            responseDtos.add(dto);
        }
        return new ResponseEntity<>(responseDtos, HttpStatus.OK);
    }
    @PostMapping()
    public ResponseEntity<FakeStoreProductResponseDto> addNewProduct(
            @RequestBody FakeStoreProductRequestDto newProductDetails
    ) {

        Product requestProduct = new Product();
        requestProduct.setId(newProductDetails.getId());
        requestProduct.setTitle(newProductDetails.getTitle());
        requestProduct.setPrice(newProductDetails.getPrice());
        requestProduct.setDescription(newProductDetails.getDescription());
        requestProduct.setImageUrl(newProductDetails.getImage());
        Category category = new Category();
        category.setName(newProductDetails.getCategory());
        requestProduct.setCategory(category);

        Product product = this.productService.addNewProduct(requestProduct);

        FakeStoreProductResponseDto responseDto = new FakeStoreProductResponseDto();
        responseDto.setProduct(product);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<FakeStoreProductResponseDto> updateProduct(@PathVariable("productId") Long productId,
                                @RequestBody FakeStoreProductRequestDto updatedProductDetails){
        Product requestProduct = new Product();
        requestProduct.setId(updatedProductDetails.getId());
        requestProduct.setTitle(updatedProductDetails.getTitle());
        requestProduct.setPrice(updatedProductDetails.getPrice());
        requestProduct.setDescription(updatedProductDetails.getDescription());
        requestProduct.setImageUrl(updatedProductDetails.getImage());
        Category category = new Category();
        category.setName(updatedProductDetails.getCategory());
        requestProduct.setCategory(category);

        Product product = this.productService.updateProduct(productId, requestProduct);
        FakeStoreProductResponseDto responseDto = new FakeStoreProductResponseDto();
        responseDto.setProduct(product);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<FakeStoreProductResponseDto> deleteProduct(@PathVariable("productId") Long productId){
        Product product = this.productService.deleteProduct(productId);
        FakeStoreProductResponseDto responseDto = new FakeStoreProductResponseDto();
        responseDto.setProduct(product);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
