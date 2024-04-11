package productservice.services;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import productservice.dtos.FakeStoreProductRequestDto;
import productservice.models.Category;
import productservice.models.Product;
import productservice.services.interfaces.ProductService;
import productservice.utility.ConvertDtoToEntity;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductServiceImplementation implements ProductService {

    private final RestTemplateBuilder restTemplateBuilder;

    public FakeStoreProductServiceImplementation(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public Product getSingleProduct(Long productId) {
        RestTemplate restTemplate = this.restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductRequestDto> productEntity = restTemplate.getForEntity(
                "https://fakestoreapi.com/products/{id}",
                FakeStoreProductRequestDto.class,
                productId
        );
        // convert product entity to product
        FakeStoreProductRequestDto productDto = productEntity.getBody();
        return ConvertDtoToEntity.ConvertFakeStoreProductDtoToProductEntity(productDto);
    }

    @Override
    public List<Product> getAllProducts() {
        RestTemplate restTemplate = this.restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductRequestDto[]> responseEntity = restTemplate.getForEntity(
                "https://fakestoreapi.com/products",
                FakeStoreProductRequestDto[].class
        );
        List<Product> products = new ArrayList<>();
        for(FakeStoreProductRequestDto productDto : responseEntity.getBody()){
            Product product = ConvertDtoToEntity.ConvertFakeStoreProductDtoToProductEntity(productDto);
            products.add(product);
        }
        return products;
    }

    @Override
    public Product addNewProduct(FakeStoreProductRequestDto productRequestDto) {
        RestTemplate restTemplate = this.restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductRequestDto> responseEntity = restTemplate.postForEntity(
                "https://fakestoreapi.com/products",
                productRequestDto,
                FakeStoreProductRequestDto.class
        );
        FakeStoreProductRequestDto productDto = responseEntity.getBody();
        return ConvertDtoToEntity.ConvertFakeStoreProductDtoToProductEntity(productDto);
    }

    @Override
    public Product updateProduct(Product product) {
        return null;
    }

    @Override
    public Boolean deleteProduct(Long productId) {
        return null;
    }
}
