package productservice.dtos;

import lombok.Getter;
import lombok.Setter;
import productservice.models.Product;
@Setter
@Getter
public class FakeStoreGetSingleProductResponseDto {
    private Product product;
}
