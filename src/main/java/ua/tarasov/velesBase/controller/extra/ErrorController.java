package ua.tarasov.velesBase.controller.extra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.tarasov.velesBase.document.extra.Error;
import ua.tarasov.velesBase.service.extra.ErrorService;
import ua.tarasov.velesBase.service.extra.TokenService;

import java.util.Date;

@RestController
@RequestMapping("/error")
public class ErrorController {

    @Autowired
    private ErrorService errorService;
    @Autowired
    private TokenService tokenService;

    private static final String MSG_ERROR = "ERROR";

    @PostMapping("/add")
    public ResponseEntity addAll(@RequestBody Iterable<Error> obs, @RequestHeader("token") String token) {
        try {
            if (!tokenService.checkToken(token)) return ResponseEntity.badRequest().body("BAD TOKEN");
            return ResponseEntity.ok(errorService.add(obs));
        } catch (Exception e) {
            errorService.add(new Error(getClass().getSimpleName()+"/add", e.getMessage(), new Date()));
            return ResponseEntity.badRequest().body(MSG_ERROR);
        }
    }

    @GetMapping("/clear")
    public ResponseEntity clear(@RequestHeader("token") String token){
        try{
            if (!tokenService.checkToken(token)) return ResponseEntity.badRequest().body("BAD TOKEN");
            return ResponseEntity.ok(errorService.clear());
        }catch (Exception e){
            errorService.add(new Error(getClass().getSimpleName()+"/clear", e.getMessage(), new Date()));
            return ResponseEntity.badRequest().body(MSG_ERROR);
        }
    }
}
