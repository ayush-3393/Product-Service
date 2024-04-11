package productservice.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Setter
@Getter
public class BaseModel {
    private Long id;
    private Date createdDate;
    private Date updatedDate;
    private Boolean isDeleted;
}
