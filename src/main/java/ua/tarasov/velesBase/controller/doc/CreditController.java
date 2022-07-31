package ua.tarasov.velesBase.controller.doc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.tarasov.velesBase.document.catalog.Client;
import ua.tarasov.velesBase.document.doc.Credit;
import ua.tarasov.velesBase.document.extra.Error;
import ua.tarasov.velesBase.service.doc.CreditService;
import ua.tarasov.velesBase.service.extra.ErrorService;
import ua.tarasov.velesBase.service.extra.TokenService;

import java.util.Date;

@RestController
@RequestMapping("/credit")
public class CreditController {

    @Autowired
    private CreditService creditService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private ErrorService errorService;

    private static final String MSG_ERROR = "ERROR";

    @PostMapping("/add")
    public ResponseEntity addAll(@RequestBody Iterable<Credit> obs, @RequestHeader("token") String token) {
        try {
            if (!tokenService.checkToken(token)) return ResponseEntity.badRequest().body("BAD TOKEN");
            return ResponseEntity.ok(creditService.addAll(obs));
        } catch (Exception e) {
            errorService.add(new Error(getClass().getSimpleName()+"/addAll", e.getMessage(), new Date()));
            return ResponseEntity.badRequest().body(MSG_ERROR);
        }
    }

    @GetMapping("/give")
    public ResponseEntity giveAll(@RequestHeader("token") String token, @RequestHeader("idAgent") String idAgent) {
        try {
            if (!tokenService.checkToken(token)) return ResponseEntity.badRequest().body("BAD TOKEN");
            StringBuilder text = new StringBuilder("DELETE FROM debs;│INSERT INTO debs ( kontr_guid, trade_guid, value_debt, value_debt_point, value_debt_doc, guid_doc, key_doc, date, comment) VALUES");
            String comma = "";
            for (Credit obj : creditService.giveAllById(idAgent)) {
                text.append(comma)
                        .append(" ('")
                        .append(obj.getIdClient())
                        .append("', '")
                        .append(obj.getIdShop())
                        .append("', ")
                        .append(obj.getCreditSum())
                        .append(", ")
                        .append(obj.getCreditShopSum())
                        .append(", ")
                        .append(obj.getCreditDocSum())
                        .append(", '")
                        .append(obj.getIdDoc())
                        .append("', '")
                        .append(obj.getNumber())
                        .append("', '")
                        .append(obj.getDate())
                        .append("', '")
                        .append(obj.getComment())
                        .append("')");
                comma = ",";
            }
            return ResponseEntity.ok(text);
        } catch (Exception e) {
            errorService.add(new Error(getClass().getSimpleName()+"/giveAll", e.getMessage(), new Date()));
            return ResponseEntity.badRequest().body(MSG_ERROR);
        }
    }

    @GetMapping("/clear")
    public ResponseEntity clear(@RequestHeader("token") String token){
        try{
            if (!tokenService.checkToken(token)) return ResponseEntity.badRequest().body("BAD TOKEN");
            return ResponseEntity.ok(creditService.clear());
        }catch (Exception e){
            errorService.add(new Error(getClass().getSimpleName()+"/clear", e.getMessage(), new Date()));
            return ResponseEntity.badRequest().body(MSG_ERROR);
        }
    }
}
