package ua.tarasov.velesBase.service.catalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.tarasov.velesBase.document.catalog.Shop;
import ua.tarasov.velesBase.document.extra.Error;
import ua.tarasov.velesBase.repository.catalog.ShopRepo;
import ua.tarasov.velesBase.service.extra.ErrorService;

import java.util.Arrays;
import java.util.Date;

@Service
public class ShopService {

    @Autowired
    private ShopRepo shopRepo;
    @Autowired
    private ErrorService errorService;

    public boolean addAll(Iterable<Shop> obs) {
        try {
            shopRepo.saveAll(obs);
        }catch (Exception e){
            errorService.add(new Error(getClass().getSimpleName()+"/addAll", e.getMessage(), new Date()));
            return false;
        }
        return true;
    }

    public boolean clear() {
        try {
            shopRepo.deleteAll();
        }catch (Exception e){
            errorService.add(new Error(getClass().getSimpleName()+"/clear", e.getMessage(), new Date()));
            return false;
        }
        return true;
    }

    public Iterable<Shop> giveAll() {
        try {
            return shopRepo.findAll();
        }catch (Exception e){
            errorService.add(new Error(getClass().getSimpleName()+"/giveAll", e.getMessage(), new Date()));
            return null;
        }
    }

    public Iterable<Shop> giveAllById(String[] value) {
        try {
            return shopRepo.findAllById(Arrays.asList(value));
        }catch (Exception e){
            errorService.add(new Error(getClass().getSimpleName()+"/giveAllById", e.getMessage(), new Date()));
            return null;
        }
    }
}
