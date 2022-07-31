package ua.tarasov.velesBase.controller.catalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.tarasov.velesBase.document.catalog.TypePrice;
import ua.tarasov.velesBase.document.extra.Error;
import ua.tarasov.velesBase.service.catalog.TypePriceService;
import ua.tarasov.velesBase.service.extra.ErrorService;
import ua.tarasov.velesBase.service.extra.TokenService;

import java.util.Date;

@RestController
@RequestMapping("/type_price")
public class TypePriceController {

    @Autowired
    private TypePriceService typePriceService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private ErrorService errorService;

    private static final String MSG_ERROR = "ERROR";

    @PostMapping("/add")
    public ResponseEntity addAll(@RequestBody Iterable<TypePrice> obs, @RequestHeader("token") String token) {
        try {
            if (!tokenService.checkToken(token)) return ResponseEntity.badRequest().body("BAD TOKEN");
            return ResponseEntity.ok(typePriceService.addAll(obs));
        } catch (Exception e) {
            errorService.add(new Error(getClass().getSimpleName()+"/addAll", e.getMessage(), new Date()));
            return ResponseEntity.badRequest().body(MSG_ERROR);
        }
    }

    @GetMapping("/give")
    public ResponseEntity giveAll(@RequestHeader("token") String token, @RequestHeader("idAgent") String idAgent) {
        try {
            if (!tokenService.checkToken(token)) return ResponseEntity.badRequest().body("BAD TOKEN");
            StringBuilder text = new StringBuilder("DELETE FROM type_prices;│INSERT INTO type_prices ( guid, name ) VALUES");
            String comma = "";
            for (TypePrice obj : typePriceService.giveAll()) {
                text.append(comma)
                        .append(" ('")
                        .append(obj.getIdTypePrice())
                        .append("', '")
                        .append(obj.getNameTypePrice())
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
            return ResponseEntity.ok(typePriceService.clear());
        }catch (Exception e){
            errorService.add(new Error(getClass().getSimpleName()+"/clear", e.getMessage(), new Date()));
            return ResponseEntity.badRequest().body(MSG_ERROR);
        }
    }

}
