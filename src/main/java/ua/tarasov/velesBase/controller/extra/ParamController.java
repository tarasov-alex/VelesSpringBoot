package ua.tarasov.velesBase.controller.extra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.tarasov.velesBase.document.extra.Error;
import ua.tarasov.velesBase.document.extra.Param;
import ua.tarasov.velesBase.service.extra.ErrorService;
import ua.tarasov.velesBase.service.extra.ParamService;
import ua.tarasov.velesBase.service.extra.TokenService;
import java.util.Date;

@RestController
@RequestMapping("/param")
public class ParamController {


    @Autowired
    private ParamService paramService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private ErrorService errorService;

    private static final String MSG_ERROR = "ERROR";
    @Value("${master.key}")
    private String keyServer;


    @PostMapping("/add")
    public ResponseEntity addAll(@RequestBody Iterable<Param> obs, @RequestHeader("token") String token) {
        try {
            if (!tokenService.checkToken(token)) return ResponseEntity.badRequest().body("BAD TOKEN");
            return ResponseEntity.ok(paramService.addAll(obs));
        } catch (Exception e) {
            errorService.add(new Error(getClass().getSimpleName()+"/addAll", e.getMessage(), new Date()));
            return ResponseEntity.badRequest().body(MSG_ERROR);
        }
    }

    @GetMapping("/clear")
    public ResponseEntity clear(@RequestHeader("token") String token, @RequestHeader("master_key") String keyClient){
        try{
            if (!keyServer.equals(keyClient)) {
                if (!tokenService.checkToken(token)) return ResponseEntity.badRequest().body("BAD TOKEN");
            }
            return ResponseEntity.ok(paramService.clear());
        }catch (Exception e){
            errorService.add(new Error(getClass().getSimpleName()+"/clear", e.getMessage(), new Date()));
            return ResponseEntity.badRequest().body(MSG_ERROR);
        }
    }

    @GetMapping("/getAll")
    private ResponseEntity getAll(@RequestHeader("token") String token) {
        try{
            if (!tokenService.checkToken(token)) return ResponseEntity.badRequest().body("BAD TOKEN");
            return ResponseEntity.ok(paramService.giveAll());
        }catch (Exception e){
            errorService.add(new Error(getClass().getSimpleName()+"/getAll", e.getMessage(), new Date()));
            return ResponseEntity.badRequest().body(MSG_ERROR);
        }
    }

    @GetMapping("one{nameParam}")
    private ResponseEntity giveOne(@RequestHeader("token") String token, @PathVariable("nameParam") String nameParam) {
        try{
            if (!tokenService.checkToken(token)) return ResponseEntity.badRequest().body("BAD TOKEN");
            return ResponseEntity.ok(paramService.giveOne(nameParam));
        }catch (Exception e){
            errorService.add(new Error(getClass().getSimpleName()+"/giveOne", e.getMessage(), new Date()));
            return ResponseEntity.badRequest().body(MSG_ERROR);
        }

    }

}
