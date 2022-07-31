package ua.tarasov.velesBase.service.extra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.tarasov.velesBase.document.extra.Error;
import ua.tarasov.velesBase.document.extra.Param;
import ua.tarasov.velesBase.repository.extra.ParamRepo;

import java.util.Date;
import java.util.Optional;

@Service
public class ParamService {

    @Autowired
    private ParamRepo paramRepo;
    @Autowired
    private ErrorService errorService;

    public boolean addAll(Iterable<Param> obs) {
        try {
            paramRepo.saveAll(obs);
        }catch (Exception e){
            errorService.add(new Error(getClass().getSimpleName()+"/addAll", e.getMessage(), new Date()));
            return false;
        }
        return true;
    }

    public boolean clear() {
        try {
            paramRepo.deleteAll();
        }catch (Exception e){
            errorService.add(new Error(getClass().getSimpleName()+"/clear", e.getMessage(), new Date()));
            return false;
        }
        return true;
    }

    public Iterable<Param> giveAll() {
        try {
            return paramRepo.findAll();
        }catch (Exception e){
            errorService.add(new Error(getClass().getSimpleName()+"/giveAll", e.getMessage(), new Date()));
            return null;
        }
    }

    public Param giveOne(String nameParam) {
        try {
            return paramRepo.findByNameParam(nameParam);
        }catch (Exception e){
            errorService.add(new Error(getClass().getSimpleName()+"/giveOne", e.getMessage(), new Date()));
            return null;
        }
    }

    public Optional<Param> giveById(String id) {
        try {
            return paramRepo.findById(id);
        } catch (Exception e) {
            errorService.add(new Error(getClass().getSimpleName() + "/giveById", e.getMessage(), new Date()));
            return null;
        }
    }
}
