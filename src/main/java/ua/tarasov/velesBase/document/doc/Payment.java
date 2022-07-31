package ua.tarasov.velesBase.document.doc;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Data
public class Payment {

    @Id
    private String idDoc = "";
    private Date date;
    private String idAgent = "";
    private String idClient = "";
    private String idShop = "";
    private String idOrganisation = "";
    private String comment = "";
    private Double sum = 0.0;

}
