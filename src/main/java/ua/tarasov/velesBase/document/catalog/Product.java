package ua.tarasov.velesBase.document.catalog;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Data
public class Product {

    @Id
    private String idProduct;
    private String idParent;
    private String idBrand;
    private String idUnit;
    private String idOrganisation;
    private String nameProduct;
    private String code;
    private String barcode;
    private String heritage;
    private String description;
    private String descriptionColor;
    private String findName;
    private String color;
    private Date dateUpdate;
    private int qBox;
    private int itGroup;
    private int itRoot;
    private int itFirst;
    private int itNew;

}
