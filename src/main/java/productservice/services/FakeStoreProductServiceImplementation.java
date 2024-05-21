package productservice.services;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import productservice.clients.FakeStoreClient;
import productservice.clients.FakeStoreProductDto;
import productservice.dtos.FakeStoreProductRequestDto;
import productservice.models.Category;
import productservice.models.Product;
import productservice.models.Rating;
import productservice.services.interfaces.ProductService;
import productservice.utility.ConvertDtoToEntity;
import productservice.utility.ConvertEntityToFakeStoreDto;
import productservice.utility.ConvertFakeStoreDtoToEntity;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductServiceImplementation implements ProductService {

    private final FakeStoreClient fakeStoreClient;

    public FakeStoreProductServiceImplementation(FakeStoreClient fakeStoreClient) {
        this.fakeStoreClient = fakeStoreClient;
    }

    @Override
    public Product getSingleProduct(Long productId) {
        FakeStoreProductDto productDto = this.fakeStoreClient.getASingleProduct(productId);
        return ConvertFakeStoreDtoToEntity.convertFakeStoreProductDtoToProductEntity(productDto);
    }

    @Override
    public List<Product> getAllProducts() {
        List<FakeStoreProductDto> productDtos = this.fakeStoreClient.getAllProducts();
        List<Product> products = new ArrayList<>();
        for (FakeStoreProductDto productDto : productDtos) {
            products.add(ConvertFakeStoreDtoToEntity.convertFakeStoreProductDtoToProductEntity(productDto));
        }
        return products;
    }

    @Override
    public Product addNewProduct(Product product) {
        FakeStoreProductDto fakeStoreProductDto =
                ConvertEntityToFakeStoreDto.convertProductEntityToFakeStoreProductDto(product);
        FakeStoreProductDto responseDto = this.fakeStoreClient.addNewProduct(fakeStoreProductDto);
        return ConvertFakeStoreDtoToEntity.convertFakeStoreProductDtoToProductEntity(responseDto);
    }

    @Override
    public Product updateProduct(Long productId, Product product) {
        FakeStoreProductDto fakeStoreProductDto =
                ConvertEntityToFakeStoreDto.convertProductEntityToFakeStoreProductDto(product);
        FakeStoreProductDto responseDto = this.fakeStoreClient.updateProduct(productId, fakeStoreProductDto);
        return ConvertFakeStoreDtoToEntity.convertFakeStoreProductDtoToProductEntity(responseDto);
    }

    @Override
    public Product deleteProduct(Long productId) {
        FakeStoreProductDto responseDto = this.fakeStoreClient.deleteProduct(productId);
        return ConvertFakeStoreDtoToEntity.convertFakeStoreProductDtoToProductEntity(responseDto);
    }
}
