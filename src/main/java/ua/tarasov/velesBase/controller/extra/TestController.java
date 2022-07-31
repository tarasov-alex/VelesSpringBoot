package ua.tarasov.velesBase.controller.extra;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping()
    private ResponseEntity testServer() {
        try{
            return ResponseEntity.ok("Сервер работает!");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка!");
        }
    }

}
