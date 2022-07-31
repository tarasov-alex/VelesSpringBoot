package ua.tarasov.velesBase.document.doc;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Data
public class OrderProduct {

    @Id
    private String genDocLine;
    private String idDoc;
    private String idAgent;
    private String idProduct;
    private String idUnitProduct;
    private String idOrganisation;
    private Double count;
    private Double countPay;
    private Double price;
    private Double pricePay;
    private Date dateUpdate;

}
