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
import java.util.Optional;

@Service
public class FakeStoreProductServiceImplementation implements ProductService {

    private final FakeStoreClient fakeStoreClient;

    public FakeStoreProductServiceImplementation(FakeStoreClient fakeStoreClient) {
        this.fakeStoreClient = fakeStoreClient;
    }

    @Override
    public Optional<Product> getSingleProduct(Long productId) {
        FakeStoreProductDto productDto = this.fakeStoreClient.getASingleProduct(productId);
        return Optional.of(ConvertFakeStoreDtoToEntity.convertFakeStoreProductDtoToProductEntity(productDto));
    }

    @Override
    public Optional<List<Product>> getAllProducts() {
        List<FakeStoreProductDto> productDtos = this.fakeStoreClient.getAllProducts();
        List<Product> products = new ArrayList<>();
        for (FakeStoreProductDto productDto : productDtos) {
            products.add(ConvertFakeStoreDtoToEntity.convertFakeStoreProductDtoToProductEntity(productDto));
        }
        return Optional.of(products);
    }

    @Override
    public Optional<Product> addNewProduct(Product product) {
        FakeStoreProductDto fakeStoreProductDto =
                ConvertEntityToFakeStoreDto.convertProductEntityToFakeStoreProductDto(product);
        FakeStoreProductDto responseDto = this.fakeStoreClient.addNewProduct(fakeStoreProductDto);
        return Optional.of(ConvertFakeStoreDtoToEntity.convertFakeStoreProductDtoToProductEntity(responseDto));
    }

    @Override
    public Optional<Product> updateProduct(Long productId, Product product) {
        FakeStoreProductDto fakeStoreProductDto =
                ConvertEntityToFakeStoreDto.convertProductEntityToFakeStoreProductDto(product);
        FakeStoreProductDto responseDto = this.fakeStoreClient.updateProduct(productId, fakeStoreProductDto);
        return Optional.of(ConvertFakeStoreDtoToEntity.convertFakeStoreProductDtoToProductEntity(responseDto));
    }

    @Override
    public Optional<Product> deleteProduct(Long productId) {
        FakeStoreProductDto responseDto = this.fakeStoreClient.deleteProduct(productId);
        return Optional.of(ConvertFakeStoreDtoToEntity.convertFakeStoreProductDtoToProductEntity(responseDto));
    }
}
