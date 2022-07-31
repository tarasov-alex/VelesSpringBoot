package ua.tarasov.velesBase.controller.doc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.tarasov.velesBase.document.doc.OrderHead;
import ua.tarasov.velesBase.document.doc.OrderProduct;
import ua.tarasov.velesBase.document.extra.Error;
import ua.tarasov.velesBase.service.doc.OrderHeadService;
import ua.tarasov.velesBase.service.doc.OrderProductService;
import ua.tarasov.velesBase.service.extra.ErrorService;
import ua.tarasov.velesBase.service.extra.TokenService;

import java.util.Date;

@RestController
@RequestMapping("/order/product")
public class OrderProductController {

    @Autowired
    private OrderProductService orderProductService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private ErrorService errorService;

    private static final String MSG_ERROR = "ERROR";



    @PostMapping("/add")
    public ResponseEntity addAll(@RequestBody Iterable<OrderProduct> obs, @RequestHeader("token") String token) {
        try {
            if (!tokenService.checkToken(token)) return ResponseEntity.badRequest().body("BAD TOKEN");
            return ResponseEntity.ok(orderProductService.addAll(obs));
        } catch (Exception e) {
            errorService.add(new Error(getClass().getSimpleName()+"/addAll", e.getMessage(), new Date()));
            return ResponseEntity.badRequest().body(MSG_ERROR);
        }
    }

    @GetMapping("/give")
    public ResponseEntity giveAll(@RequestHeader("token") String token, @RequestHeader("idAgent") String idAgent) {
        try {
            if (!tokenService.checkToken(token)) return ResponseEntity.badRequest().body("BAD TOKEN");
            StringBuilder text = new StringBuilder("DELETE FROM orders_line;│INSERT INTO orders_line ( order_guid, product_guid, amount, price, summa_pay ) VALUES");
            String comma = "";
            for (OrderProduct obj : orderProductService.giveAllByIdAgent(idAgent)) {
                text.append(comma)
                        .append(" ('").append(obj.getIdDoc())
                        .append("', '").append(obj.getIdProduct())
                        .append("', ").append(obj.getCount())
                        .append(", ").append(obj.getPrice())
                        .append(", ").append(obj.getPricePay())
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
            return ResponseEntity.ok(orderProductService.clear());
        }catch (Exception e){
            errorService.add(new Error(getClass().getSimpleName()+"/clear", e.getMessage(), new Date()));
            return ResponseEntity.badRequest().body(MSG_ERROR);
        }
    }
}
