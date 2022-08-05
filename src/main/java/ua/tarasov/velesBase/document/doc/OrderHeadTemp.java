package ua.tarasov.velesBase.document.doc;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Data
public class OrderHeadTemp {

    @Id
    private String idDoc;
    private String idOrganisation;
    private Date date;
    private String idAgent;
    private String idClient;
    private String idShop;
    private String idStore;
    private String idPriceType;
    private String comment;
    private String gps;
    private int typePay;
    private int nonCash;
    private int useCert;
    private Double discount;

}
