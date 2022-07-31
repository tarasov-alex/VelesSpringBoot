package ua.tarasov.velesBase.document.catalog;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Data
public class Client {

    @Id
    private String idClient;
    private String idOrganisation;
    private String nameClient;
    private String inn;
    private String code;
    private String findName;
    private Date dateUpdate;

}
