package ua.tarasov.velesBase.controller.calculus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.tarasov.velesBase.document.calculus.Balance;
import ua.tarasov.velesBase.document.calculus.Price;
import ua.tarasov.velesBase.document.extra.Error;
import ua.tarasov.velesBase.service.calculus.PriceService;
import ua.tarasov.velesBase.service.extra.ErrorService;
import ua.tarasov.velesBase.service.extra.TokenService;

import java.util.Date;

@RestController
@RequestMapping("/price")
public class PriceController {

    @Autowired
    private PriceService priceService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private ErrorService errorService;

    private static final String MSG_ERROR = "ERROR";

    @PostMapping("/add")
    public ResponseEntity addAll(@RequestBody Iterable<Price> obs, @RequestHeader("token") String token){
        try {
            if (!tokenService.checkToken(token)) return ResponseEntity.badRequest().body("BAD TOKEN");
            return ResponseEntity.ok(priceService.addAll(obs));
        } catch (Exception e) {
            errorService.add(new Error(getClass().getSimpleName()+"/addAll", e.getMessage(), new Date()));
            return ResponseEntity.badRequest().body(MSG_ERROR);
        }
    }

    @GetMapping("/give")
    public ResponseEntity giveAll(@RequestHeader("token") String token, @RequestHeader("idAgent") String idAgent) {
        try {
            if (!tokenService.checkToken(token)) return ResponseEntity.badRequest().body("BAD TOKEN");
            StringBuilder text = new StringBuilder("DELETE FROM prices;│INSERT INTO prices ( product_guid, price_guid, value) VALUES");
            String comma = "";
            for (Price obj : priceService.giveAll()) {
                text.append(comma)
                        .append(" ('")
                        .append(obj.getIdProduct())
                        .append("', '")
                        .append(obj.getIdTypePrice())
                        .append("', ")
                        .append(obj.getPrice())
                        .append(")");
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
            return ResponseEntity.ok(priceService.clear());
        }catch (Exception e){
            errorService.add(new Error(getClass().getSimpleName()+"/clear", e.getMessage(), new Date()));
            return ResponseEntity.badRequest().body(MSG_ERROR);
        }
    }
}
