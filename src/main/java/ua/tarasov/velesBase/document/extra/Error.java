package ua.tarasov.velesBase.document.extra;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Date;

@Document
@Data
public class Error {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String idUser;
    private String nameMethod;
    private String message;
    private Date date;

    public Error(String idUser, String nameMethod, String message, Date date) {
        this.idUser = idUser;
        this.nameMethod = nameMethod;
        this.message = message;
        this.date = date;
    }

    public Error(String nameMethod, String message, Date date) {
        this.idUser = "backend";
        this.nameMethod = nameMethod;
        this.message = message;
        this.date = date;
    }
}
