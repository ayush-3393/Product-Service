package productservice.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Product extends BaseModel{
    private String title;
    private Double price;
    private String description;
    private Category category;
    private String imageUrl;
    private Rating rating;
}
