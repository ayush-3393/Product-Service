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

@Service
public class FakeStoreCategoryServiceImplementation implements CategoryService {

    private final FakeStoreClient fakeStoreClient;

    public FakeStoreCategoryServiceImplementation(FakeStoreClient fakeStoreClient) {
        this.fakeStoreClient = fakeStoreClient;
    }

    @Override
    public List<Category> getAllCategories() {
        FakeStoreCategoryDto fakeStoreCategoryDto = this.fakeStoreClient.getAllCategories();
        List<Category> categories = new ArrayList<>();
        for(String category : fakeStoreCategoryDto.getCategory()) {
            Category newCategory = new Category();
            newCategory.setName(category);
            categories.add(newCategory);
        }
        return categories;
    }

    @Override
    public List<Product> getProductsInCategory(Category category) {
        List<FakeStoreProductDto> productDtos = this.fakeStoreClient.getProductsInCategory(category);
        List<Product> products = new ArrayList<>();
        for (FakeStoreProductDto productDto : productDtos) {
            products.add(ConvertFakeStoreDtoToEntity.convertFakeStoreProductDtoToProductEntity(productDto));
        }
        return products;
    }
}
