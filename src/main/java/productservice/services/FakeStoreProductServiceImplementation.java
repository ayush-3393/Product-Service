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
        if (productDto == null) {
            return Optional.empty();
        }
        return Optional.of(ConvertFakeStoreDtoToEntity.convertFakeStoreProductDtoToProductEntity(productDto));
    }

    @Override
    public Optional<List<Product>> getAllProducts() {
        List<FakeStoreProductDto> productDtos = this.fakeStoreClient.getAllProducts();
        if (productDtos == null) {
            return Optional.empty();
        }
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
        if (responseDto == null) {
            return Optional.empty();
        }
        return Optional.of(ConvertFakeStoreDtoToEntity.convertFakeStoreProductDtoToProductEntity(responseDto));
    }

    @Override
    public Optional<Product> updateProduct(Long productId, Product product) {
        FakeStoreProductDto fakeStoreProductDto =
                ConvertEntityToFakeStoreDto.convertProductEntityToFakeStoreProductDto(product);
        FakeStoreProductDto responseDto = this.fakeStoreClient.updateProduct(productId, fakeStoreProductDto);
        if (responseDto == null) {
            return Optional.empty();
        }
        return Optional.of(ConvertFakeStoreDtoToEntity.convertFakeStoreProductDtoToProductEntity(responseDto));
    }

    @Override
    public Optional<Product> deleteProduct(Long productId) {
        FakeStoreProductDto responseDto = this.fakeStoreClient.deleteProduct(productId);
        if (responseDto == null) {
            return Optional.empty();
        }
        return Optional.of(ConvertFakeStoreDtoToEntity.convertFakeStoreProductDtoToProductEntity(responseDto));
    }
}
