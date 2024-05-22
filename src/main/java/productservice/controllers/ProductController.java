package productservice.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import productservice.dtos.FakeStoreProductResponseDto;
import productservice.dtos.FakeStoreProductRequestDto;
import productservice.exceptions.NotFoundException;
import productservice.models.Category;
import productservice.models.Product;
import productservice.services.interfaces.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{productId}")
    public ResponseEntity<FakeStoreProductResponseDto> getSingleProduct(@PathVariable("productId") Long productId) throws NotFoundException {
        Optional<Product> product = this.productService.getSingleProduct(productId);
        if (product.isEmpty()){
            throw new NotFoundException("Product not found");
        }
        FakeStoreProductResponseDto responseDto = new FakeStoreProductResponseDto();
        responseDto.setProduct(product.get());
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<FakeStoreProductResponseDto>> getAllProducts() throws NotFoundException {
        Optional<List<Product>> allProducts = this.productService.getAllProducts();
        if (allProducts.isEmpty()){
            throw new NotFoundException("No products found");
        }
        List<FakeStoreProductResponseDto> responseDtos = new ArrayList<>();
        for(Product product : allProducts.get()){
            FakeStoreProductResponseDto dto = new FakeStoreProductResponseDto();
            dto.setProduct(product);
            responseDtos.add(dto);
        }
        return new ResponseEntity<>(responseDtos, HttpStatus.OK);
    }
    @PostMapping()
    public ResponseEntity<FakeStoreProductResponseDto> addNewProduct(
            @RequestBody FakeStoreProductRequestDto newProductDetails
    ) throws NotFoundException {

        Product requestProduct = new Product();
        requestProduct.setId(newProductDetails.getId());
        requestProduct.setTitle(newProductDetails.getTitle());
        requestProduct.setPrice(newProductDetails.getPrice());
        requestProduct.setDescription(newProductDetails.getDescription());
        requestProduct.setImageUrl(newProductDetails.getImage());
        Category category = new Category();
        category.setName(newProductDetails.getCategory());
        requestProduct.setCategory(category);

        Optional<Product> product = this.productService.addNewProduct(requestProduct);

        if (product.isEmpty()){
            throw new NotFoundException("Product not found");
        }

        FakeStoreProductResponseDto responseDto = new FakeStoreProductResponseDto();
        responseDto.setProduct(product.get());
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<FakeStoreProductResponseDto> updateProduct(@PathVariable("productId") Long productId,
                                @RequestBody FakeStoreProductRequestDto updatedProductDetails) throws NotFoundException {
        Product requestProduct = new Product();
        requestProduct.setId(updatedProductDetails.getId());
        requestProduct.setTitle(updatedProductDetails.getTitle());
        requestProduct.setPrice(updatedProductDetails.getPrice());
        requestProduct.setDescription(updatedProductDetails.getDescription());
        requestProduct.setImageUrl(updatedProductDetails.getImage());
        Category category = new Category();
        category.setName(updatedProductDetails.getCategory());
        requestProduct.setCategory(category);

        Optional<Product> product = this.productService.updateProduct(productId, requestProduct);
        if (product.isEmpty()){
            throw new NotFoundException("Product not found");
        }
        FakeStoreProductResponseDto responseDto = new FakeStoreProductResponseDto();
        responseDto.setProduct(product.get());
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<FakeStoreProductResponseDto> deleteProduct(@PathVariable("productId") Long productId) throws NotFoundException {
        Optional<Product> product = this.productService.deleteProduct(productId);
        if (product.isEmpty()){
            throw new NotFoundException("Product not found");
        }
        FakeStoreProductResponseDto responseDto = new FakeStoreProductResponseDto();
        responseDto.setProduct(product.get());
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
