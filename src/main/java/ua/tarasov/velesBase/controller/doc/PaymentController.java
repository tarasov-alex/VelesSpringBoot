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
    public ResponseEntity addAll(@RequestBody Iterable<Payment> obs, @RequestHeader("token") String token, @RequestHeader("idOrganisation") String idOrganisation) {
        try {
            if (!tokenService.checkToken(token,idOrganisation)) return ResponseEntity.badRequest().body("BAD TOKEN");
            return ResponseEntity.ok(paymentService.addAll(obs));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(writeError("addAll", e));
        }
    }

    @GetMapping("/giveAllOfOrganisation")
    public ResponseEntity giveAllOfOrganisation(@RequestHeader("token") String token, @RequestHeader("idAgent") String idAgent, @RequestHeader("idOrganisation") String idOrganisation) {
        try {
            if (!tokenService.checkToken(token,idOrganisation)) return ResponseEntity.badRequest().body("BAD TOKEN");
            return ResponseEntity.ok(paymentService.giveAllByIdAgent(idAgent));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(writeError("giveAllOfOrganisation", e));
        }
    }

    @DeleteMapping("/deleteByIdDoc")
    public ResponseEntity deleteByIdDoc(@RequestBody Iterable<String> ids, @RequestHeader("token") String token, @RequestHeader("idOrganisation") String idOrganisation){
        try{
            if (!tokenService.checkToken(token,idOrganisation)) return ResponseEntity.badRequest().body("BAD TOKEN");
            return ResponseEntity.ok(paymentService.deleteAllByIdDoc(ids));
        }catch (Exception e){
            errorService.add(new Error(getClass().getSimpleName()+"/deleteByIdDoc", e.getMessage(), new Date()));
            return ResponseEntity.badRequest().body(MSG_ERROR);
        }
    }

    @DeleteMapping("/clear")
    public ResponseEntity clear(@RequestHeader("token") String token, @RequestHeader("idOrganisation") String idOrganisation){
        try{
            if (!tokenService.checkToken(token,idOrganisation)) return ResponseEntity.badRequest().body("BAD TOKEN");
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
