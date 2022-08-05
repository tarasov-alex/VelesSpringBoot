package ua.tarasov.velesBase.service.doc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.tarasov.velesBase.document.doc.OrderHeadTemp;
import ua.tarasov.velesBase.document.extra.Error;
import ua.tarasov.velesBase.repository.doc.OrderHeadTempRepo;
import ua.tarasov.velesBase.service.extra.ErrorService;
import java.util.Date;

@Service
public class OrderHeadTempService {

    @Autowired
    private OrderHeadTempRepo orderHeadTempRepo;
    @Autowired
    private ErrorService errorService;

    public boolean addAll(Iterable<OrderHeadTemp> obs) {
        try {
            orderHeadTempRepo.saveAll(obs);
        }catch (Exception e){
            errorService.add(new Error(getClass().getSimpleName()+"/addAll", e.getMessage(), new Date()));
            return false;
        }
        return true;
    }

    public boolean clear() {
        try {
            orderHeadTempRepo.deleteAll();
        }catch (Exception e){
            errorService.add(new Error(getClass().getSimpleName()+"/clear", e.getMessage(), new Date()));
            return false;
        }
        return true;
    }

    public Iterable<OrderHeadTemp> giveAllByIdAgent(String idAgent) {
        try {
            return orderHeadTempRepo.findByIdAgent(idAgent);
        }catch (Exception e){
            errorService.add(new Error(getClass().getSimpleName()+"/giveAllByIdAgent", e.getMessage(), new Date()));
            return null;
        }
    }

    public boolean clearByAgent(String idAgent) {
        try {
            orderHeadTempRepo.deleteByIdAgent(idAgent);
        }catch (Exception e){
            errorService.add(new Error(getClass().getSimpleName()+"/clearByAgent", e.getMessage(), new Date()));
            return false;
        }
        return true;
    }

    public boolean deleteAllByIdDoc(Iterable<String> ids) {
        try {
            orderHeadTempRepo.deleteAllById(ids);
            return true;
        }
        catch (Exception e){
            errorService.add(new Error(getClass().getSimpleName()+"/deleteAllByIdDoc", e.getMessage(), new Date()));
            return false;
        }
    }
}
