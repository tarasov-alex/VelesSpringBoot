package ua.tarasov.velesBase.service.calculus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.tarasov.velesBase.document.calculus.Balance;
import ua.tarasov.velesBase.document.extra.Error;
import ua.tarasov.velesBase.repository.calculus.BalanceRepo;
import ua.tarasov.velesBase.service.extra.ErrorService;

import java.util.Date;

@Service
public class BalanceService {

    @Autowired
    private BalanceRepo balanceRepo;
    @Autowired
    private ErrorService errorService;

    public boolean addAll(Iterable<Balance> obs) {
        try {
            balanceRepo.saveAll(obs);
        }catch (Exception e){
            errorService.add(new Error(getClass().getSimpleName()+"/addAll", e.getMessage(), new Date()));
            return false;
        }
        return true;
    }

    public boolean clear() {
        try {
            balanceRepo.deleteAll();
        }catch (Exception e){
            errorService.add(new Error(getClass().getSimpleName()+"/clear", e.getMessage(), new Date()));
            return false;
        }
        return true;
    }

    public Iterable<Balance> giveAll() {
        try {
            return balanceRepo.findAll();
        }catch (Exception e){
            errorService.add(new Error(getClass().getSimpleName()+"/giveAll", e.getMessage(), new Date()));
            return null;
        }
    }

    public Iterable<Balance> giveAllOfOrganisation(String idOrganisation) {
        try {
            return balanceRepo.findAllByIdOrganisation(idOrganisation);
        }catch (Exception e){
            errorService.add(new Error(getClass().getSimpleName()+"/giveAllOfOrganisation", e.getMessage(), new Date()));
            return null;
        }
    }
}
