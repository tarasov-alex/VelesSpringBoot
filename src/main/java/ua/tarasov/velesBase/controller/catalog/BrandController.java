package ua.tarasov.velesBase.controller.catalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.tarasov.velesBase.document.catalog.Brand;
import ua.tarasov.velesBase.document.extra.Error;
import ua.tarasov.velesBase.service.catalog.BrandService;
import ua.tarasov.velesBase.service.extra.ErrorService;
import ua.tarasov.velesBase.service.extra.TokenService;

import java.util.Date;

@RestController
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private ErrorService errorService;

    private static final String MSG_ERROR = "ERROR";

    @PostMapping("/add")
    public ResponseEntity addAll(@RequestBody Iterable<Brand> obs, @RequestHeader("token") String token) {
        try {
            if (!tokenService.checkToken(token)) return ResponseEntity.badRequest().body("BAD TOKEN");
            return ResponseEntity.ok(brandService.addAll(obs));
        } catch (Exception e) {
            errorService.add(new Error(getClass().getSimpleName()+"/addAll", e.getMessage(), new Date()));
            return ResponseEntity.badRequest().body(MSG_ERROR);
        }
    }

    @GetMapping("/give")
    public ResponseEntity giveAll(@RequestHeader("token") String token, @RequestHeader("idAgent") String idAgent) {
        try {
            if (!tokenService.checkToken(token)) return ResponseEntity.badRequest().body("BAD TOKEN");
            StringBuilder text = new StringBuilder("DELETE FROM brends;│INSERT INTO brends ( guid, name ) VALUES");
            String comma = "";
            for (Brand obj : brandService.giveAll()) {
                text.append(comma)
                        .append(" ('")
                        .append(obj.getIdBrand())
                        .append("', '")
                        .append(obj.getNameBrand())
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
            return ResponseEntity.ok(brandService.clear());
        }catch (Exception e){
            errorService.add(new Error(getClass().getSimpleName()+"/clear", e.getMessage(), new Date()));
            return ResponseEntity.badRequest().body(MSG_ERROR);
        }
    }
}
