package ua.tarasov.velesBase.service.doc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.tarasov.velesBase.document.doc.Credit;
import ua.tarasov.velesBase.document.doc.Payment;
import ua.tarasov.velesBase.document.extra.Error;
import ua.tarasov.velesBase.repository.doc.CreditRepo;
import ua.tarasov.velesBase.repository.doc.PaymentRepo;
import ua.tarasov.velesBase.service.extra.ErrorService;

import java.util.Date;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepo paymentRepo;
    @Autowired
    private ErrorService errorService;

    public boolean addAll(Iterable<Payment> obs) {
        try {
            paymentRepo.saveAll(obs);
        }catch (Exception e){
            errorService.add(new Error(getClass().getSimpleName()+"/addAll", e.getMessage(), new Date()));
            return false;
        }
        return true;
    }

    public boolean clear() {
        try {
            paymentRepo.deleteAll();
        }catch (Exception e){
            errorService.add(new Error(getClass().getSimpleName()+"/clear", e.getMessage(), new Date()));
            return false;
        }
        return true;
    }

    public Iterable<Payment> giveAll() {
        try {
            return paymentRepo.findAll();
        }catch (Exception e){
            errorService.add(new Error(getClass().getSimpleName()+"/giveAll", e.getMessage(), new Date()));
            return null;
        }
    }

    public Iterable<Payment> giveAllByIdAgent(String id) {
        try {
            return paymentRepo.findByIdAgent(id);
        }catch (Exception e){
            errorService.add(new Error(getClass().getSimpleName()+"/giveAllByIdAgent", e.getMessage(), new Date()));
            return null;
        }
    }
}
