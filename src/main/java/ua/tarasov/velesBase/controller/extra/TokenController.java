package ua.tarasov.velesBase.controller.extra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.tarasov.velesBase.GenerationToken;
import ua.tarasov.velesBase.document.extra.Error;
import ua.tarasov.velesBase.document.extra.Token;
import ua.tarasov.velesBase.service.extra.ErrorService;
import ua.tarasov.velesBase.service.extra.TokenService;
import ua.tarasov.velesBase.service.extra.UserService;

import java.util.Date;

@RestController
@RequestMapping("/token")
public class TokenController {

    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private ErrorService errorService;

    private static final String MSG_ERROR = "ERROR";

    @GetMapping()
    private ResponseEntity get_token(@RequestHeader("imei") String imei) {
        try{
            if (userService.checkUserByImei(imei)){

                String tk = GenerationToken.newToken();

                Token new_token = new Token();
                new_token.setToken(tk);
                new_token.setImei(imei);
                new_token.setDate(new Date());

                if (tokenService.add(new_token)){
                    return ResponseEntity.ok(tk);
                }
            }
            return ResponseEntity.badRequest().body("NO IMEI");
        }catch (Exception e){
            errorService.add(new Error(getClass().getSimpleName()+"/get_token", e.getMessage(), new Date()));
            return ResponseEntity.badRequest().body(MSG_ERROR);
        }
    }

}
