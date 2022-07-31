package ua.tarasov.velesBase.service.calculus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.tarasov.velesBase.document.calculus.Price;
import ua.tarasov.velesBase.document.catalog.Brand;
import ua.tarasov.velesBase.document.extra.Error;
import ua.tarasov.velesBase.repository.calculus.PriceRepo;
import ua.tarasov.velesBase.service.extra.ErrorService;

import java.util.Date;

@Service
public class PriceService {

    @Autowired
    private PriceRepo priceRepo;
    @Autowired
    private ErrorService errorService;

    public boolean addAll(Iterable<Price> obs) {
        try {
            priceRepo.saveAll(obs);
        }catch (Exception e){
            errorService.add(new Error(getClass().getSimpleName()+"/addAll", e.getMessage(), new Date()));
            return false;
        }
        return true;
    }

    public boolean clear() {
        try {
            priceRepo.deleteAll();
        }catch (Exception e){
            errorService.add(new Error(getClass().getSimpleName()+"/clear", e.getMessage(), new Date()));
            return false;
        }
        return true;
    }

    public Iterable<Price> giveAll() {
        try {
            return priceRepo.findAll();
        }catch (Exception e){
            errorService.add(new Error(getClass().getSimpleName()+"/giveAll", e.getMessage(), new Date()));
            return null;
        }
    }
}
