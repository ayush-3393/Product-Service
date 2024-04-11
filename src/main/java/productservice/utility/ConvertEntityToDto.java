package productservice.utility;

import productservice.dtos.FakeStoreProductRequestDto;
import productservice.models.Product;

public class ConvertEntityToDto {

    public static FakeStoreProductRequestDto ConvertProductEntityToFakeStoreProductDto(Product product){
        FakeStoreProductRequestDto productDto = new FakeStoreProductRequestDto();
        productDto.setId(product.getId());
        productDto.setTitle(product.getTitle());
        productDto.setPrice(product.getPrice());
        productDto.setDescription(product.getDescription());
        productDto.setCategory(product.getCategory().getName());
        productDto.setImage(product.getImageUrl());
        return productDto;
    }

}
