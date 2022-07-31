package ua.tarasov.velesBase.document.extra;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Param {

    @Id
    private String id;
    private String idOrganisation;
    private String nameParam;
    private String object;
    private String value1;
    private String[] value2;

}
