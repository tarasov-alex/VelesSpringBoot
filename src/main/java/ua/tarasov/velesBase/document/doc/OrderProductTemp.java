package ua.tarasov.velesBase.document.doc;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Data
public class OrderProductTemp {

    @Id
    private String id;
    private String idDoc = "";
    private String idAgent = "";
    private String idProduct = "";
    private String idOrganisation = "";
    private Double count = 0.0;

}
