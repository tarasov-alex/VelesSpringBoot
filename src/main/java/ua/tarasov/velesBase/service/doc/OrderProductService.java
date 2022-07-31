package ua.tarasov.velesBase.service.doc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.tarasov.velesBase.document.doc.OrderHead;
import ua.tarasov.velesBase.document.doc.OrderProduct;
import ua.tarasov.velesBase.document.extra.Error;
import ua.tarasov.velesBase.repository.doc.OrderHeadRepo;
import ua.tarasov.velesBase.repository.doc.OrderProductRepo;
import ua.tarasov.velesBase.service.extra.ErrorService;

import java.util.Date;

@Service
public class OrderProductService {

    @Autowired
    private OrderProductRepo orderProductRepo;
    @Autowired
    private ErrorService errorService;

    public boolean addAll(Iterable<OrderProduct> obs) {
        try {
            orderProductRepo.saveAll(obs);
        }catch (Exception e){
            errorService.add(new Error(getClass().getSimpleName()+"/addAll", e.getMessage(), new Date()));
            return false;
        }
        return true;
    }

    public boolean clear() {
        try {
            orderProductRepo.deleteAll();
        }catch (Exception e){
            errorService.add(new Error(getClass().getSimpleName()+"/clear", e.getMessage(), new Date()));
            return false;
        }
        return true;
    }

    public Iterable<OrderProduct> giveAllByIdAgent(String idAgent) {
        try {
            return orderProductRepo.findByIdAgent(idAgent);
        }catch (Exception e){
            errorService.add(new Error(getClass().getSimpleName()+"/giveAllByIdAgent", e.getMessage(), new Date()));
            return null;
        }
    }
}
