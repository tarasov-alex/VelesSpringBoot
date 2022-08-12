package ua.tarasov.velesBase.controller.extra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.tarasov.velesBase.document.catalog.Client;
import ua.tarasov.velesBase.document.extra.Error;
import ua.tarasov.velesBase.document.extra.User;
import ua.tarasov.velesBase.service.extra.ErrorService;
import ua.tarasov.velesBase.service.extra.TokenService;
import ua.tarasov.velesBase.service.extra.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private ErrorService errorService;

    private static final String MSG_ERROR = "ERROR";

    @Value("${master.key}")
    private String keyServer;


    @PostMapping("/add")
    public ResponseEntity addAll(@RequestBody Iterable<User> obs, @RequestHeader("token") String token, @RequestHeader("master_key") String keyClient, @RequestHeader("idOrganisation") String idOrganisation) {
        try {
            if (!keyServer.equals(keyClient)) {
                if (!tokenService.checkToken(token,idOrganisation)) return ResponseEntity.badRequest().body("BAD TOKEN");
            }
            return ResponseEntity.ok(userService.addAll(obs));
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
            return ResponseEntity.ok(userService.clear());
        }catch (Exception e){
            errorService.add(new Error(getClass().getSimpleName()+"/clear", e.getMessage(), new Date()));
            return ResponseEntity.badRequest().body(MSG_ERROR);
        }
    }
}
