package productservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Product extends BaseModel{
    private String title;
    private Double price;
    private String description;
    @ManyToOne
    private Category category;
    private String imageUrl;
    @Transient
    private Rating rating;
}
