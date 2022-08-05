package ua.tarasov.velesBase.service.doc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.tarasov.velesBase.document.doc.OrderProduct;
import ua.tarasov.velesBase.document.doc.OrderProductTemp;
import ua.tarasov.velesBase.document.extra.Error;
import ua.tarasov.velesBase.repository.doc.OrderProductRepo;
import ua.tarasov.velesBase.repository.doc.OrderProductTempRepo;
import ua.tarasov.velesBase.service.extra.ErrorService;

import java.util.Date;

@Service
public class OrderProductTempService {

    @Autowired
    private OrderProductTempRepo orderProductTempRepo;
    @Autowired
    private ErrorService errorService;

    public boolean addAll(Iterable<OrderProductTemp> obs) {
        try {
            orderProductTempRepo.saveAll(obs);
        }catch (Exception e){
            errorService.add(new Error(getClass().getSimpleName()+"/addAll", e.getMessage(), new Date()));
            return false;
        }
        return true;
    }

    public boolean clear() {
        try {
            orderProductTempRepo.deleteAll();
        }catch (Exception e){
            errorService.add(new Error(getClass().getSimpleName()+"/clear", e.getMessage(), new Date()));
            return false;
        }
        return true;
    }

    public boolean deleteByIdDocIn(Iterable<String> ids) {
        try {
            orderProductTempRepo.deleteByIdDocIn(ids);
        }catch (Exception e){
            errorService.add(new Error(getClass().getSimpleName()+"/deleteByIdDocIn", e.getMessage(), new Date()));
            return false;
        }
        return true;
    }

    public Iterable<OrderProductTemp> giveAllByIdAgent(String idAgent) {
        try {
            return orderProductTempRepo.findByIdAgent(idAgent);
        }catch (Exception e){
            errorService.add(new Error(getClass().getSimpleName()+"/giveAllByIdAgent", e.getMessage(), new Date()));
            return null;
        }
    }
}
