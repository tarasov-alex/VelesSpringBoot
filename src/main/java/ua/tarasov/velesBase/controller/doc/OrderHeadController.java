package ua.tarasov.velesBase.controller.doc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.tarasov.velesBase.document.doc.OrderHead;
import ua.tarasov.velesBase.document.extra.Error;
import ua.tarasov.velesBase.model.UpdateObj;
import ua.tarasov.velesBase.service.doc.OrderHeadService;
import ua.tarasov.velesBase.service.doc.OrderProductService;
import ua.tarasov.velesBase.service.extra.ErrorService;
import ua.tarasov.velesBase.service.extra.TokenService;

import java.util.Date;

@RestController
@RequestMapping("/order")
public class OrderHeadController {

    @Autowired
    private OrderProductService orderProductService;
    @Autowired
    private OrderHeadService orderHeadService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private ErrorService errorService;

    private static final String MSG_ERROR = "ERROR";



    @PostMapping("/add")
    public ResponseEntity addAll(@RequestBody Iterable<OrderHead> obs, @RequestHeader("token") String token) {
        try {
            if (!tokenService.checkToken(token)) return ResponseEntity.badRequest().body("BAD TOKEN");
            return ResponseEntity.ok(orderHeadService.addAll(obs));
        } catch (Exception e) {
            errorService.add(new Error(getClass().getSimpleName()+"/addAll", e.getMessage(), new Date()));
            return ResponseEntity.badRequest().body(MSG_ERROR);
        }
    }

    @GetMapping("/give")
    public ResponseEntity giveAll(@RequestHeader("token") String token, @RequestHeader("idAgent") String idAgent) {
        try {
            if (!tokenService.checkToken(token)) return ResponseEntity.badRequest().body("BAD TOKEN");
            StringBuilder text = new StringBuilder("INSERT INTO orders ( date, find_name, guid, number, selling, send, kontr_guid, trade_guid, comment, summa, first_form, discount, summa_pay ) VALUES");
            String comma = "";
            for (OrderHead obj : orderHeadService.giveAllByIdAgent(idAgent)) {
                text.append(comma)
                        .append(" ('").append(obj.getDate().getTime())
                        .append("', '").append(obj.getFindName())
                        .append("', '").append(obj.getIdDoc())
                        .append("', '").append(obj.getNumber())
                        .append("', '").append(obj.getNumberPay())
                        .append("', ").append(obj.getSendToBase())
                        .append(", '").append(obj.getIdClient())
                        .append("', '").append(obj.getIdShop())
                        .append("', '").append(obj.getComment())
                        .append("', ").append(obj.getSum())
                        .append(", ").append(obj.getNonCash())
                        .append(", ").append(obj.getDiscount())
                        .append(", ").append(obj.getSumPay())
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
            return ResponseEntity.ok(orderHeadService.clear());
        }catch (Exception e){
            errorService.add(new Error(getClass().getSimpleName()+"/clear", e.getMessage(), new Date()));
            return ResponseEntity.badRequest().body(MSG_ERROR);
        }
    }

    @PostMapping("/getListUpdate")
    public ResponseEntity giveNotUpdate(@RequestBody Iterable<UpdateObj> obs, @RequestHeader("token") String token){
        try{
            if (!tokenService.checkToken(token)) return ResponseEntity.badRequest().body("BAD TOKEN");
            return ResponseEntity.ok(orderHeadService.giveListNotUpdateDoc(obs));
        }catch (Exception e){
            errorService.add(new Error(getClass().getSimpleName()+"/clear", e.getMessage(), new Date()));
            return ResponseEntity.badRequest().body(MSG_ERROR);
        }
    }

}
