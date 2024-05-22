package productservice.utility;

import productservice.clients.FakeStoreClient.FakeStoreProductDto;
import productservice.models.Category;
import productservice.models.Product;
import productservice.models.Rating;

public class ConvertFakeStoreDtoToEntity {
    public static Product convertFakeStoreProductDtoToProductEntity(FakeStoreProductDto productDto){
        Product product = new Product();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        Category category = new Category();
        category.setName(productDto.getCategory());
        product.setCategory(category);
        product.setImageUrl(productDto.getImage());
        if (productDto.getRating() != null) {
            Rating rating = new Rating();
            rating.setCount(productDto.getRating().getCount());
            rating.setRate(productDto.getRating().getRate());
            product.setRating(rating);
        }
        return product;
    }
}
