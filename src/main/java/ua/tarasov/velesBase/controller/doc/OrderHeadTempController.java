package ua.tarasov.velesBase.controller.doc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.tarasov.velesBase.document.doc.OrderHeadTemp;
import ua.tarasov.velesBase.document.extra.Error;
import ua.tarasov.velesBase.service.doc.OrderHeadTempService;
import ua.tarasov.velesBase.service.extra.ErrorService;
import ua.tarasov.velesBase.service.extra.TokenService;

import java.util.Date;

@RestController
@RequestMapping("/orderTemp")
public class OrderHeadTempController {

    @Autowired
    private OrderHeadTempService orderHeadTempService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private ErrorService errorService;
    @Value("${master.key}")
    private String keyServer;

    private static final String MSG_ERROR = "ERROR";



    @PostMapping("/add")
    public ResponseEntity addAll(@RequestBody Iterable<OrderHeadTemp> obs, @RequestHeader("token") String token, @RequestHeader("idOrganisation") String idOrganisation) {
        try {
            if (!tokenService.checkToken(token,idOrganisation)) return ResponseEntity.badRequest().body("BAD TOKEN");
            return ResponseEntity.ok(orderHeadTempService.addAll(obs));
        } catch (Exception e) {
            errorService.add(new Error(getClass().getSimpleName()+"/addAll", e.getMessage(), new Date()));
            return ResponseEntity.badRequest().body(MSG_ERROR);
        }
    }

    @GetMapping("/giveAllOfOrganisation")
    public ResponseEntity giveAllOfOrganisation(@RequestHeader("token") String token, @RequestHeader("idAgent") String idAgent, @RequestHeader("idOrganisation") String idOrganisation) {
        try {
            if (!tokenService.checkToken(token,idOrganisation)) return ResponseEntity.badRequest().body("BAD TOKEN");
            return ResponseEntity.ok(orderHeadTempService.giveAllByIdAgent(idAgent));
        } catch (Exception e) {
            errorService.add(new Error(getClass().getSimpleName()+"/giveAllOfOrganisation", e.getMessage(), new Date()));
            return ResponseEntity.badRequest().body(MSG_ERROR);
        }
    }

    @DeleteMapping("/deleteByIdDoc")
    public ResponseEntity deleteByIdDoc(@RequestBody Iterable<String> ids, @RequestHeader("token") String token, @RequestHeader("idOrganisation") String idOrganisation){
        try{
            if (!tokenService.checkToken(token,idOrganisation)) return ResponseEntity.badRequest().body("BAD TOKEN");
            return ResponseEntity.ok(orderHeadTempService.deleteAllByIdDoc(ids));
        }catch (Exception e){
            errorService.add(new Error(getClass().getSimpleName()+"/deleteByIdDoc", e.getMessage(), new Date()));
            return ResponseEntity.badRequest().body(MSG_ERROR);
        }
    }

    @DeleteMapping("/clearAll")
    public ResponseEntity clearAll(@RequestHeader("token") String token, @RequestHeader("master_key") String keyClient, @RequestHeader("idOrganisation") String idOrganisation){
        try{
            if (!keyServer.equals(keyClient)) {
                if (!tokenService.checkToken(token,idOrganisation)) return ResponseEntity.badRequest().body("BAD TOKEN");
            }
            return ResponseEntity.ok(orderHeadTempService.clear());
        }catch (Exception e){
            errorService.add(new Error(getClass().getSimpleName()+"/clearAll", e.getMessage(), new Date()));
            return ResponseEntity.badRequest().body(MSG_ERROR);
        }
    }

//    @PostMapping("/getListUpdate")
//    public ResponseEntity giveNotUpdate(@RequestBody Iterable<UpdateObj> obs, @RequestHeader("token") String token){
//        try{
//            if (!tokenService.checkToken(token,idOrganisation)) return ResponseEntity.badRequest().body("BAD TOKEN");
//            return ResponseEntity.ok(orderHeadService.giveListNotUpdateDoc(obs));
//        }catch (Exception e){
//            errorService.add(new Error(getClass().getSimpleName()+"/clear", e.getMessage(), new Date()));
//            return ResponseEntity.badRequest().body(MSG_ERROR);
//        }
//    }

}
