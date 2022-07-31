package ua.tarasov.velesBase.controller.catalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.tarasov.velesBase.document.catalog.Client;
import ua.tarasov.velesBase.document.extra.Error;
import ua.tarasov.velesBase.document.extra.Param;
import ua.tarasov.velesBase.service.catalog.ClientService;
import ua.tarasov.velesBase.service.extra.ErrorService;
import ua.tarasov.velesBase.service.extra.ParamService;
import ua.tarasov.velesBase.service.extra.TokenService;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private ErrorService errorService;
    @Autowired
    private ParamService paramService;

    private static final String MSG_ERROR = "ERROR";
    private static final String LABEL_CLIENT = "client_agent_";

    @PostMapping("/add")
    public ResponseEntity addAll(@RequestBody Iterable<Client> obs, @RequestHeader("token") String token) {
        try {
            if (!tokenService.checkToken(token)) return ResponseEntity.badRequest().body("BAD TOKEN");
            return ResponseEntity.ok(clientService.addAll(obs));
        } catch (Exception e) {
            errorService.add(new Error(getClass().getSimpleName()+"/addAll", e.getMessage(), new Date()));
            return ResponseEntity.badRequest().body(MSG_ERROR);
        }
    }

    @GetMapping("/give")
    public ResponseEntity giveAll(@RequestHeader("token") String token, @RequestHeader("idAgent") String idAgent) {
        try {
            if (!tokenService.checkToken(token)) return ResponseEntity.badRequest().body("BAD TOKEN");

            Optional<Param> param = paramService.giveById(LABEL_CLIENT + idAgent);
            Iterable<Client> clients;
            if (param.isEmpty()) clients = clientService.giveAll();
            else clients = clientService.giveAllById(param.get().getValue2());

            StringBuilder text = new StringBuilder("DELETE FROM clients;│INSERT INTO clients ( guid, name, find_name, inn, okpo ) VALUES");
            String comma = "";
            for (Client obj : clients) {
                text.append(comma)
                        .append(" ('")
                        .append(obj.getIdClient())
                        .append("', '")
                        .append(obj.getNameClient())
                        .append("', '")
                        .append(obj.getFindName())
                        .append("', '")
                        .append(obj.getInn())
                        .append("', '")
                        .append(obj.getCode())
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
            return ResponseEntity.ok(clientService.clear());
        }catch (Exception e){
            errorService.add(new Error(getClass().getSimpleName()+"/clear", e.getMessage(), new Date()));
            return ResponseEntity.badRequest().body(MSG_ERROR);
        }
    }
}
