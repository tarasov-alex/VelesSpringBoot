package ua.tarasov.velesBase.controller.doc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.tarasov.velesBase.document.doc.Payment;
import ua.tarasov.velesBase.document.extra.Error;
import ua.tarasov.velesBase.service.doc.PaymentService;
import ua.tarasov.velesBase.service.extra.ErrorService;
import ua.tarasov.velesBase.service.extra.TokenService;

import java.util.Date;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private ErrorService errorService;

    private static final String MSG_ERROR = "ERROR";

    @PostMapping("/add")
    public ResponseEntity addAll(@RequestBody Iterable<Payment> obs, @RequestHeader("token") String token) {
        try {
            if (!tokenService.checkToken(token)) return ResponseEntity.badRequest().body("BAD TOKEN");
            return ResponseEntity.ok(paymentService.addAll(obs));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(writeError("addAll", e));
        }
    }

    @GetMapping("/give")
    public ResponseEntity giveAll(@RequestHeader("token") String token, @RequestHeader("idAgent") String idAgent) {
        try {
            if (!tokenService.checkToken(token)) return ResponseEntity.badRequest().body("BAD TOKEN");
            StringBuilder text = new StringBuilder("DELETE FROM debs;│INSERT INTO debs ( kontr_guid, trade_guid, value_debt, value_debt_point, value_debt_doc, guid_doc, key_doc, date, comment) VALUES");
            String comma = "";
            for (Payment obj : paymentService.giveAllByIdAgent(idAgent)) {
//                text.append(comma)
//                        .append(" ('")
//                        .append(obj.getIdClient())
//                        .append("', '")
//                        .append(obj.getIdShop())
//                        .append("', ")
//                        .append(obj.getCreditSum())
//                        .append(", ")
//                        .append(obj.getCreditShopSum())
//                        .append(", ")
//                        .append(obj.getCreditDocSum())
//                        .append(", '")
//                        .append(obj.getIdDoc())
//                        .append("', '")
//                        .append(obj.getNumber())
//                        .append("', '")
//                        .append(obj.getDate())
//                        .append("', '")
//                        .append(obj.getComment())
//                        .append("')");
                comma = ",";
            }
            return ResponseEntity.ok(text);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(writeError("giveAll", e));
        }
    }

    @GetMapping("/clear")
    public ResponseEntity clear(@RequestHeader("token") String token){
        try{
            if (!tokenService.checkToken(token)) return ResponseEntity.badRequest().body("BAD TOKEN");
            return ResponseEntity.ok(paymentService.clear());
        }catch (Exception e){
            return ResponseEntity.badRequest().body(writeError("clear", e));
        }
    }

    private String writeError(String nameMethod, Exception e){
        errorService.add(new Error(getClass().getSimpleName()+"/" + nameMethod, e.getMessage(), new Date()));
        return MSG_ERROR;
    }
}
