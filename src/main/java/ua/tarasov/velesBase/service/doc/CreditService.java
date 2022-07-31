package ua.tarasov.velesBase.service.doc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.tarasov.velesBase.document.catalog.Client;
import ua.tarasov.velesBase.document.doc.Credit;
import ua.tarasov.velesBase.document.extra.Error;
import ua.tarasov.velesBase.repository.doc.CreditRepo;
import ua.tarasov.velesBase.service.extra.ErrorService;

import java.util.Date;

@Service
public class CreditService {

    @Autowired
    private CreditRepo creditRepo;
    @Autowired
    private ErrorService errorService;

    public boolean addAll(Iterable<Credit> obs) {
        try {
            creditRepo.saveAll(obs);
        }catch (Exception e){
            errorService.add(new Error(getClass().getSimpleName()+"/addAll", e.getMessage(), new Date()));
            return false;
        }
        return true;
    }

    public boolean clear() {
        try {
            creditRepo.deleteAll();
        }catch (Exception e){
            errorService.add(new Error(getClass().getSimpleName()+"/clear", e.getMessage(), new Date()));
            return false;
        }
        return true;
    }

    public Iterable<Credit> giveAll() {
        try {
            return creditRepo.findAll();
        }catch (Exception e){
            errorService.add(new Error(getClass().getSimpleName()+"/giveAll", e.getMessage(), new Date()));
            return null;
        }
    }

    public Iterable<Credit> giveAllById(String id) {
        try {
            return creditRepo.findByIdAgent(id);
        }catch (Exception e){
            errorService.add(new Error(getClass().getSimpleName()+"/giveAllById", e.getMessage(), new Date()));
            return null;
        }
    }
}
