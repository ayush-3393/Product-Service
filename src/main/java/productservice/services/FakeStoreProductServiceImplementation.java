package productservice.services;

import org.springframework.stereotype.Service;
import productservice.clients.FakeStoreClient.FakeStoreClient;
import productservice.clients.FakeStoreClient.FakeStoreProductDto;
import productservice.models.Product;
import productservice.services.interfaces.ProductService;
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
