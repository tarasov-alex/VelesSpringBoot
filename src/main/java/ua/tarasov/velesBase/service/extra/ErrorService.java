package ua.tarasov.velesBase.service.extra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.tarasov.velesBase.document.extra.Error;
import ua.tarasov.velesBase.repository.extra.ErrorRepo;

@Service
public class ErrorService {

    @Autowired
    private ErrorRepo errorRepo;

    public Boolean add(Iterable<Error> errors){
        try {
            errorRepo.saveAll(errors);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public Boolean add(Error error){
        try {
            errorRepo.save(error);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public Boolean clear(){
        try {
            errorRepo.deleteAll();
        }catch (Exception e){
            return false;
        }
        return true;
    }


}
