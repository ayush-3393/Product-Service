package productservice.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import productservice.dtos.FakeStoreGetSingleProductResponseDto;
import productservice.dtos.FakeStoreProductRequestDto;
import productservice.models.Product;
import productservice.services.interfaces.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{productId}")
    public ResponseEntity<FakeStoreGetSingleProductResponseDto> getSingleProduct(@PathVariable("productId") Long productId){
        Product product = this.productService.getSingleProduct(productId);
        FakeStoreGetSingleProductResponseDto responseDto = new FakeStoreGetSingleProductResponseDto();
        responseDto.setProduct(product);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/")
    public String getAllProducts(){
        return "All products";
    }
    @PutMapping()
    public String addNewProduct(@RequestBody FakeStoreProductRequestDto newProductDetails){
        return "Added new product";
    }
    @PatchMapping("/{productId}")
    public String updateProduct(@PathVariable("productId") Long productId,
                                @RequestBody FakeStoreProductRequestDto updatedProductDetails){
        return "Updated product";
    }
}
