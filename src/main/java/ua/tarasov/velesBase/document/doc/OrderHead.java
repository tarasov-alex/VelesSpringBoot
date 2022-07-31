package ua.tarasov.velesBase.document.doc;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Data
public class OrderHead {

    @Id
    private String idDoc;
    private String idOrganisation;
    private Date date;
    private String number;
    private String numberPay;
    private String idAgent;
    private String idClient;
    private String idShop;
    private String idStore;
    private String idPriceType;
    private String comment;
    private String findName;
    private int typePay;
    private int nonCash;
    private int useCert;
    private int sendToBase;
    private Double sum;
    private Double sumPay;
    private Double discount;
    private Date dateUpdate;

}
