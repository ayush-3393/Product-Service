package productservice.services;

import org.springframework.stereotype.Service;
import productservice.clients.FakeStoreClient.FakeStoreCategoryDto;
import productservice.clients.FakeStoreClient.FakeStoreClient;
import productservice.clients.FakeStoreClient.FakeStoreProductDto;
import productservice.models.Category;
import productservice.models.Product;
import productservice.services.interfaces.CategoryService;
import productservice.utility.ConvertFakeStoreDtoToEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FakeStoreCategoryServiceImplementation implements CategoryService {

    private final FakeStoreClient fakeStoreClient;

    public FakeStoreCategoryServiceImplementation(FakeStoreClient fakeStoreClient) {
        this.fakeStoreClient = fakeStoreClient;
    }

    @Override
    public Optional<List<Category>> getAllCategories() {
        FakeStoreCategoryDto fakeStoreCategoryDto = this.fakeStoreClient.getAllCategories();
        if (fakeStoreCategoryDto == null) {
            return Optional.empty();
        }
        List<Category> categories = new ArrayList<>();
        for(String category : fakeStoreCategoryDto.getCategory()) {
            Category newCategory = new Category();
            newCategory.setName(category);
            categories.add(newCategory);
        }
        return Optional.of(categories);
    }

    @Override
    public Optional<List<Product>> getProductsInCategory(Category category) {
        List<FakeStoreProductDto> productDtos = this.fakeStoreClient.getProductsInCategory(category);
        if (productDtos == null) {
            return Optional.empty();
        }
        List<Product> products = new ArrayList<>();
        for (FakeStoreProductDto productDto : productDtos) {
            products.add(ConvertFakeStoreDtoToEntity.convertFakeStoreProductDtoToProductEntity(productDto));
        }
        return Optional.of(products);
    }
}
