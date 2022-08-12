package ua.tarasov.velesBase.service.catalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.tarasov.velesBase.document.catalog.Unit;
import ua.tarasov.velesBase.document.extra.Error;
import ua.tarasov.velesBase.repository.catalog.UnitRepo;
import ua.tarasov.velesBase.service.extra.ErrorService;

import java.util.Date;

@Service
public class UnitService {

    @Autowired
    private UnitRepo unitRepo;
    @Autowired
    private ErrorService errorService;

    public boolean addAll(Iterable<Unit> obs) {
        try {
            unitRepo.saveAll(obs);
        }catch (Exception e){
            errorService.add(new Error(getClass().getSimpleName()+"/addAll", e.getMessage(), new Date()));
            return false;
        }
        return true;
    }

    public boolean clear() {
        try {
            unitRepo.deleteAll();
        }catch (Exception e){
            errorService.add(new Error(getClass().getSimpleName()+"/clear", e.getMessage(), new Date()));
            return false;
        }
        return true;
    }

    public Iterable<Unit> giveAll() {
        try {
            return unitRepo.findAll();
        }catch (Exception e){
            errorService.add(new Error(getClass().getSimpleName()+"/giveAll", e.getMessage(), new Date()));
            return null;
        }
    }

    public Iterable<Unit> giveAllOfOrganisation(String idOrganisation) {
        try {
            return unitRepo.findAllByIdOrganisation(idOrganisation);
        }catch (Exception e){
            errorService.add(new Error(getClass().getSimpleName()+"/giveAllOfOrganisation", e.getMessage(), new Date()));
            return null;
        }
    }
}
