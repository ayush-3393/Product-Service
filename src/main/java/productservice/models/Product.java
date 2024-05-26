package productservice.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Setter
@Getter
@Entity
public class Product extends BaseModel{
    private String title;
    private Double price;
    private String description;
    @ManyToOne
    @Cascade(CascadeType.ALL)
    @JsonManagedReference
    private Category category;
    private String imageUrl;
    @Transient
    private Rating rating;
}
