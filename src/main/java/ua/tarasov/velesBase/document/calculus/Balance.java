package ua.tarasov.velesBase.document.calculus;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Date;

@Document
@Data
public class Balance {

    @Id
    private String genIdBalance;
    private String idProduct;
    private String idStore;
    private String idOrganisation;
    private Double count;
    private Date dateUpdate;

}
