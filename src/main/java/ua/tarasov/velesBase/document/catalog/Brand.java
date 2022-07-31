package ua.tarasov.velesBase.document.catalog;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Data
public class Brand {

    @Id
    private String idBrand;
    private String idOrganisation;
    private String nameBrand;
    private Date dateUpdate;

}
