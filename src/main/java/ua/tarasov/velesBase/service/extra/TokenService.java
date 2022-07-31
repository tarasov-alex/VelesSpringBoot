package ua.tarasov.velesBase.service.extra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.tarasov.velesBase.document.extra.Error;
import ua.tarasov.velesBase.document.extra.Param;
import ua.tarasov.velesBase.document.extra.Token;
import ua.tarasov.velesBase.repository.extra.TokenRepo;

import java.util.Date;

@Service
public class TokenService {

    @Autowired
    private TokenRepo tokenRepo;
    @Autowired
    private ErrorService errorService;
    @Autowired
    private ParamService paramService;

    private static final String TIME_LIFE_TOKEN = "time_life_token";

    public Boolean add(Token token){
        try {
            tokenRepo.save(token);
        }catch (Exception e){
            errorService.add(new Error(getClass().getSimpleName()+"/add", e.getMessage(), new Date()));
            return false;
        }
        return true;
    }

    //Проверка существования токена
    public Boolean checkToken(String token){
        try {
            //Находим токен в таблице токенов
            Token check_token = tokenRepo.findByToken(token);
            //Если ответ не НУЛЛ, тогда..
            if (check_token != null) {
                //От текущей даты отнимаем дату создания токена, получаем миллисекунды и делим
                //на 60000, чтобы получить минуты
                long sec = ((new Date()).getTime() - check_token.getDate().getTime()) / 60000;
                //Получаем параметр, сколько должен действовать токен
                Param time_life_token = paramService.giveOne(TIME_LIFE_TOKEN);
                //Если нет такого параметра, по умолчанию 5 минут
                if (time_life_token == null) return sec < 10;
                //Если есть, преобразуем в число и сравниваем
                else return sec < Integer.parseInt(time_life_token.getValue1());
            }
        } catch (Exception e) {
            errorService.add(new Error(getClass().getSimpleName() + "/checkToken", e.getMessage(), new Date()));
        }
        return false;
    }

    public Boolean clear(){
        try {
            tokenRepo.deleteAll();
        }catch (Exception e){
            return false;
        }
        return true;
    }

}
