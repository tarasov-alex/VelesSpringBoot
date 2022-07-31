package ua.tarasov.velesBase.document.catalog;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Data
public class Unit {

    @Id
    private String idUnit;
    private String idOrganisation;
    private String nameUnit;
    private Date dateUpdate;

}
