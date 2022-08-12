package ua.tarasov.velesBase.service.extra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.tarasov.velesBase.document.catalog.Client;
import ua.tarasov.velesBase.document.extra.Error;
import ua.tarasov.velesBase.document.extra.User;
import ua.tarasov.velesBase.repository.extra.UserRepo;

import java.util.Date;
import java.util.stream.StreamSupport;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ErrorService errorService;

    public boolean addAll(Iterable<User> obs) {
        try {
            userRepo.saveAll(obs);
        }catch (Exception e){
            errorService.add(new Error(getClass().getSimpleName()+"/addAll", e.getMessage(), new Date()));
            return false;
        }
        return true;
    }

    public boolean clear() {
        try {
            userRepo.deleteAll();
        }catch (Exception e){
            errorService.add(new Error(getClass().getSimpleName()+"/clear", e.getMessage(), new Date()));
            return false;
        }
        return true;
    }

    public Iterable<User> giveAll() {
        try {
            return userRepo.findAll();
        }catch (Exception e){
            errorService.add(new Error(getClass().getSimpleName()+"/giveAll", e.getMessage(), new Date()));
            return null;
        }
    }

    public boolean checkUserByImei(String imei) {
        try {
            return StreamSupport.stream(userRepo.findByImei(imei).spliterator(), false).findAny().isPresent();
        }catch (Exception e){
            errorService.add(new Error(getClass().getSimpleName()+"/giveAll", e.getMessage(), new Date()));
            return false;
        }
    }
}
