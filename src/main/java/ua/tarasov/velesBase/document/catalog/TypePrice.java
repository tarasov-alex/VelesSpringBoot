package ua.tarasov.velesBase.document.catalog;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Data
public class TypePrice {

    @Id
    private String idTypePrice;
    private String idOrganisation;
    private String nameTypePrice;
    private Date dateUpdate;

}
