package ua.tarasov.velesBase.controller.catalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.tarasov.velesBase.document.catalog.Client;
import ua.tarasov.velesBase.document.catalog.Shop;
import ua.tarasov.velesBase.document.extra.Error;
import ua.tarasov.velesBase.document.extra.Param;
import ua.tarasov.velesBase.service.catalog.ShopService;
import ua.tarasov.velesBase.service.extra.ErrorService;
import ua.tarasov.velesBase.service.extra.ParamService;
import ua.tarasov.velesBase.service.extra.TokenService;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    private ShopService shopService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private ErrorService errorService;
    @Autowired
    private ParamService paramService;
    private static final String MSG_ERROR = "ERROR";
    private static final String LABEL_SHOP = "shop_agent_";

    @PostMapping("/add")
    public ResponseEntity addAll(@RequestBody Iterable<Shop> obs, @RequestHeader("token") String token, @RequestHeader("idOrganisation") String idOrganisation) {
        try {
            if (!tokenService.checkToken(token,idOrganisation)) return ResponseEntity.badRequest().body("BAD TOKEN");
            return ResponseEntity.ok(shopService.addAll(obs));
        } catch (Exception e) {
            errorService.add(new Error(getClass().getSimpleName()+"/addAll", e.getMessage(), new Date()));
            return ResponseEntity.badRequest().body(MSG_ERROR);
        }
    }

    @GetMapping("/giveAllOfOrganisation")
    public ResponseEntity giveAllOfOrganisation(@RequestHeader("token") String token, @RequestHeader("idAgent") String idAgent, @RequestHeader("idOrganisation") String idOrganisation) {
        try {
            if (!tokenService.checkToken(token,idOrganisation)) return ResponseEntity.badRequest().body("BAD TOKEN");

            Optional<Param> param = paramService.giveById(LABEL_SHOP + idAgent);
            Iterable<Shop> shops;
            if (param.isEmpty()) shops = shopService.giveAllOfOrganisation(idOrganisation);
            else shops = shopService.giveAllById(param.get().getValue2());

            StringBuilder text = new StringBuilder("DELETE FROM trade_points;│INSERT INTO trade_points ( guid, name, find_name, kontr_guid, phone, respite, city, active, type_price_guid ) VALUES");
            String comma = "";
            for (Shop obj : shops) {
                text.append(comma)
                        .append(" ('")
                        .append(obj.getIdShop())
                        .append("', '")
                        .append(obj.getNameShop())
                        .append("', '")
                        .append(obj.getFindName())
                        .append("', '")
                        .append(obj.getIdClient())
                        .append("', '")
                        .append(obj.getPhone())
                        .append("', ")
                        .append(obj.getDelay())
                        .append(", '")
                        .append(obj.getCity())
                        .append("', ")
                        .append(obj.getActive())
                        .append(", '")
                        .append(obj.getIdTypePrice())
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
            return ResponseEntity.ok(shopService.clear());
        }catch (Exception e){
            errorService.add(new Error(getClass().getSimpleName()+"/clear", e.getMessage(), new Date()));
            return ResponseEntity.badRequest().body(MSG_ERROR);
        }
    }
}
