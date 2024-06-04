package productservice.models;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Rating {
    private Double rate;
    private Integer count;
}
