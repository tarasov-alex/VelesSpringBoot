package ua.tarasov.velesBase.document.catalog;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Data
public class Shop {

    @Id
    private String idShop;
    private String idClient;
    private String idOrganisation;
    private String idTypePrice;
    private String nameShop;
    private String phone;
    private String city;
    private String findName;
    private int delay;
    private int active;
    private Date dateUpdate;

}
