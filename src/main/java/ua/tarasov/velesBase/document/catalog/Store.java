package ua.tarasov.velesBase.document.catalog;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Data
public class Store {

    @Id
    private String idStore;
    private String idOrganisation;
    private String nameStore;
    private Date dateUpdate;

}
