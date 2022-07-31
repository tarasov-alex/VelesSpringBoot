package ua.tarasov.velesBase.document.extra;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class User {

    @Id
    private String idUser;
    private String name;
    private String imei;
    private String password;

}
