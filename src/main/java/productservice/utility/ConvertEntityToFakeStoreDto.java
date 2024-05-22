package productservice.utility;

import productservice.clients.FakeStoreClient.FakeStoreProductDto;
import productservice.models.Product;

public class ConvertEntityToFakeStoreDto {
    public static FakeStoreProductDto convertProductEntityToFakeStoreProductDto(Product product){
        FakeStoreProductDto productDto = new FakeStoreProductDto();
        productDto.setId(product.getId());
        productDto.setTitle(product.getTitle());
        productDto.setPrice(product.getPrice());
        productDto.setDescription(product.getDescription());
        productDto.setCategory(product.getCategory().getName());
        productDto.setImage(product.getImageUrl());
//        FakeStoreRatingDto rating = new FakeStoreRatingDto();
//        if (product.getRating().getCount() == null) {
//            rating.setCount(null);
//        }
//        if ()
//        rating.setRate(product.getRating().getRate());
//        productDto.setRating(rating);
        return productDto;
    }
}
