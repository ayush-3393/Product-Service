package productservice.dtos;

import lombok.Getter;
import lombok.Setter;
import productservice.models.Category;

import java.util.List;

@Setter
@Getter
public class FakeStoreCategoryResponseDto {
    List<Category> category;
}
