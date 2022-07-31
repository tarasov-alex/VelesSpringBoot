package ua.tarasov.velesBase.service.catalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.tarasov.velesBase.document.catalog.Store;
import ua.tarasov.velesBase.document.extra.Error;
import ua.tarasov.velesBase.repository.doc.catalog.StoreRepo;
import ua.tarasov.velesBase.service.extra.ErrorService;

import java.util.Date;

@Service
public class StoreService {

    @Autowired
    private StoreRepo storeRepo;
    @Autowired
    private ErrorService errorService;

    public boolean addAll(Iterable<Store> obs) {
        try {
            storeRepo.saveAll(obs);
        }catch (Exception e){
            errorService.add(new Error(getClass().getSimpleName()+"/addAll", e.getMessage(), new Date()));
            return false;
        }
        return true;
    }

    public boolean clear() {
        try {
            storeRepo.deleteAll();
        }catch (Exception e){
            errorService.add(new Error(getClass().getSimpleName()+"/clear", e.getMessage(), new Date()));
            return false;
        }
        return true;
    }

    public Iterable<Store> giveAll() {
        try {
            return storeRepo.findAll();
        }catch (Exception e){
            errorService.add(new Error(getClass().getSimpleName()+"/giveAll", e.getMessage(), new Date()));
            return null;
        }
    }
}
