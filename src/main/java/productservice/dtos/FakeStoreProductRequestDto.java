package productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FakeStoreProductRequestDto {
    private Long id;
    private String title;
    private Double price;
    private String description;
    private String image;
    private String category;
}
