package ua.tarasov.velesBase.service.catalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.tarasov.velesBase.document.catalog.TypePrice;
import ua.tarasov.velesBase.document.extra.Error;
import ua.tarasov.velesBase.repository.catalog.TypePriceRepo;
import ua.tarasov.velesBase.service.extra.ErrorService;

import java.util.Date;

@Service
public class TypePriceService {

    @Autowired
    private TypePriceRepo typePriceRepo;
    @Autowired
    private ErrorService errorService;

    public boolean addAll(Iterable<TypePrice> obs) {
        try {
            typePriceRepo.saveAll(obs);
        }catch (Exception e){
            errorService.add(new Error(getClass().getSimpleName()+"/addAll", e.getMessage(), new Date()));
            return false;
        }
        return true;
    }

    public boolean clear() {
        try {
            typePriceRepo.deleteAll();
        }catch (Exception e){
            errorService.add(new Error(getClass().getSimpleName()+"/clear", e.getMessage(), new Date()));
            return false;
        }
        return true;
    }

    public Iterable<TypePrice> giveAll() {
        try {
            return typePriceRepo.findAll();
        }catch (Exception e){
            errorService.add(new Error(getClass().getSimpleName()+"/giveAll", e.getMessage(), new Date()));
            return null;
        }
    }
}
