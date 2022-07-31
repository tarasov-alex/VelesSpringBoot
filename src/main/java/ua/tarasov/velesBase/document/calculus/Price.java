package ua.tarasov.velesBase.document.calculus;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


@Document
@Data
public class Price {

    @Id
    private String genIdPrice;
    private String idProduct;
    private String idTypePrice;
    private String idOrganisation;
    private Double price;
    private Date dateUpdate;

}
