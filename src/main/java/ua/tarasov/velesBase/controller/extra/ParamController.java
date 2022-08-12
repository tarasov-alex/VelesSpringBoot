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

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

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
    public ResponseEntity addAll(@RequestBody Iterable<Param> obs, @RequestHeader("token") String token, @RequestHeader("idOrganisation") String idOrganisation) {
        try {
            if (!tokenService.checkToken(token,idOrganisation)) return ResponseEntity.badRequest().body("BAD TOKEN");
            return ResponseEntity.ok(paramService.addAll(obs));
        } catch (Exception e) {
            errorService.add(new Error(getClass().getSimpleName()+"/addAll", e.getMessage(), new Date()));
            return ResponseEntity.badRequest().body(MSG_ERROR);
        }
    }

    @GetMapping("/clear")
    public ResponseEntity clear(@RequestHeader("token") String token, @RequestHeader("master_key") String keyClient, @RequestHeader("idOrganisation") String idOrganisation){
        try{
            if (!keyServer.equals(keyClient)) {
                if (!tokenService.checkToken(token,idOrganisation)) return ResponseEntity.badRequest().body("BAD TOKEN");
            }
            return ResponseEntity.ok(paramService.clear());
        }catch (Exception e){
            errorService.add(new Error(getClass().getSimpleName()+"/clear", e.getMessage(), new Date()));
            return ResponseEntity.badRequest().body(MSG_ERROR);
        }
    }

    @GetMapping("/getAll")
    private ResponseEntity getAll(@RequestHeader("token") String token, @RequestHeader("idOrganisation") String idOrganisation) {
        try{
            if (!tokenService.checkToken(token,idOrganisation)) return ResponseEntity.badRequest().body("BAD TOKEN");
            return ResponseEntity.ok(paramService.giveAll());
        }catch (Exception e){
            errorService.add(new Error(getClass().getSimpleName()+"/getAll", e.getMessage(), new Date()));
            return ResponseEntity.badRequest().body(MSG_ERROR);
        }
    }

    @GetMapping("/giveDefParamAgent")
    private ResponseEntity giveDefParamAgent(@RequestHeader("token") String token, @RequestHeader("idAgent") String idAgent, @RequestHeader("idOrganisation") String idOrganisation) {
        try{
            if (!tokenService.checkToken(token,idOrganisation)) return ResponseEntity.badRequest().body("BAD TOKEN");

            StringBuilder text = new StringBuilder();

            Optional<Param> param = paramService.giveById("default_settings_agent_"+idAgent);
            if (param.isPresent()){
                for (String element : param.get().getValue2()){
                    String[] lines = element.split("│");
                    text.append("UPDATE settings SET value = '").append(lines[1]).append("' WHERE name = '").append(lines[0]).append("'│");
                }
            }
            return ResponseEntity.ok(text.substring(0, text.length() - 1));
        }catch (Exception e){
            errorService.add(new Error(getClass().getSimpleName()+"/giveDefParamAgent", e.getMessage(), new Date()));
            return ResponseEntity.badRequest().body(MSG_ERROR);
        }
    }

    @GetMapping("one")
    private ResponseEntity giveOneParam(@RequestHeader("token") String token, @PathVariable("nameParam") String nameParam, @RequestHeader("idOrganisation") String idOrganisation) {
        try{
            if (!tokenService.checkToken(token,idOrganisation)) return ResponseEntity.badRequest().body("BAD TOKEN");
            Optional<Param> param = paramService.giveOneParam(nameParam);
            if (param.isPresent()) return ResponseEntity.ok(param.get());
            else return ResponseEntity.ok("");
        }catch (Exception e){
            errorService.add(new Error(getClass().getSimpleName()+"/giveOneParam", e.getMessage(), new Date()));
            return ResponseEntity.badRequest().body(MSG_ERROR);
        }

    }

}
