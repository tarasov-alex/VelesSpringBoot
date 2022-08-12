package ua.tarasov.velesBase.controller.catalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.tarasov.velesBase.document.catalog.Client;
import ua.tarasov.velesBase.document.catalog.Product;
import ua.tarasov.velesBase.document.extra.Error;
import ua.tarasov.velesBase.document.extra.Param;
import ua.tarasov.velesBase.service.catalog.ProductService;
import ua.tarasov.velesBase.service.extra.ErrorService;
import ua.tarasov.velesBase.service.extra.ParamService;
import ua.tarasov.velesBase.service.extra.TokenService;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private ErrorService errorService;
    @Autowired
    private ParamService paramService;

    private static final String MSG_ERROR = "ERROR";
    private static final String LABEL_PRODUCT = "product_agent_";

    @PostMapping("/add")
    public ResponseEntity addAll(@RequestBody Iterable<Product> obs, @RequestHeader("token") String token, @RequestHeader("idOrganisation") String idOrganisation) {
        try {
            if (!tokenService.checkToken(token,idOrganisation)) return ResponseEntity.badRequest().body("BAD TOKEN");
            return ResponseEntity.ok(productService.addAll(obs));
        } catch (Exception e) {
            errorService.add(new Error(getClass().getSimpleName()+"/addAll", e.getMessage(), new Date()));
            return ResponseEntity.badRequest().body(MSG_ERROR);
        }
    }

    @GetMapping("/giveAllOfOrganisation")
    public ResponseEntity giveAllOfOrganisation(@RequestHeader("token") String token, @RequestHeader("idAgent") String idAgent, @RequestHeader("idOrganisation") String idOrganisation) {
        try {
            if (!tokenService.checkToken(token,idOrganisation)) return ResponseEntity.badRequest().body("BAD TOKEN");

            Optional<Param> param = paramService.giveById(LABEL_PRODUCT + idAgent);
            List<Product> products;
            Iterable<Product> productsHead;
            Iterable<Product> productsTemp;
            HashSet<Product> hashSet = new HashSet<>();

            if (param.isEmpty()) products = StreamSupport.stream(productService.giveAllOfOrganisation(idOrganisation).spliterator(), false)
                    .collect(Collectors.toList());
            else{
                productsHead = productService.giveAllById(param.get().getValue2());
                for (Product objHead: productsHead){
                    productsTemp = productService.giveAllLike(objHead.getHeritage());
                    for (Product objTemp: productsTemp){
                        if (objTemp.equals(objHead)){
                            objTemp.setItRoot(1);
                        }
                        hashSet.add(objTemp);
                    }
                }
                products = new ArrayList<>(hashSet);
            }

            StringBuilder text = new StringBuilder("DELETE FROM products;│INSERT INTO products ( guid, parent_guid, unit_guid, groups, root, artikul, barcode, name, find_name, q_box, guid_brend, color, heritage ) VALUES");
            String comma = "";
            for (Product obj: products){
                text.append(comma)
                        .append(" ('").append(obj.getIdProduct())
                        .append("', '").append(obj.getIdParent())
                        .append("', '").append(obj.getIdUnit())
                        .append("', ").append(obj.getItGroup())
                        .append(", ").append(obj.getItRoot())
                        .append(", '").append(obj.getCode())
                        .append("', '").append(obj.getBarcode())
                        .append("', '").append(obj.getNameProduct())
                        .append("', '").append(obj.getFindName())
                        .append("', ").append(obj.getQBox())
                        .append(", '").append(obj.getIdBrand())
                        .append("', '").append(obj.getColor())
                        .append("', '").append(obj.getHeritage())
                        .append("')");
                comma = ",";
            }
            return ResponseEntity.ok(text);
        } catch (Exception e) {
            errorService.add(new Error(getClass().getSimpleName()+"/giveAllOfOrganisation", e.getMessage(), new Date()));
            return ResponseEntity.badRequest().body(MSG_ERROR);
        }
    }

    @GetMapping("/clear")
    public ResponseEntity clear(@RequestHeader("token") String token, @RequestHeader("idOrganisation") String idOrganisation){
        try{
            if (!tokenService.checkToken(token,idOrganisation)) return ResponseEntity.badRequest().body("BAD TOKEN");
            return ResponseEntity.ok(productService.clear());
        }catch (Exception e){
            errorService.add(new Error(getClass().getSimpleName()+"/clear", e.getMessage(), new Date()));
            return ResponseEntity.badRequest().body(MSG_ERROR);
        }
    }

}
