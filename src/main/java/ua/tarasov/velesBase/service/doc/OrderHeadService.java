package ua.tarasov.velesBase.service.doc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.tarasov.velesBase.document.catalog.Client;
import ua.tarasov.velesBase.document.catalog.Product;
import ua.tarasov.velesBase.document.doc.OrderHead;
import ua.tarasov.velesBase.document.doc.OrderProduct;
import ua.tarasov.velesBase.document.extra.Error;
import ua.tarasov.velesBase.model.UpdateObj;
import ua.tarasov.velesBase.repository.doc.OrderHeadRepo;
import ua.tarasov.velesBase.repository.doc.OrderProductRepo;
import ua.tarasov.velesBase.service.extra.ErrorService;

import java.lang.reflect.Array;
import java.util.*;

@Service
public class OrderHeadService {

    @Autowired
    private OrderHeadRepo orderHeadRepo;
    @Autowired
    private ErrorService errorService;

    public boolean addAll(Iterable<OrderHead> obs) {
        try {
            orderHeadRepo.saveAll(obs);
        }catch (Exception e){
            errorService.add(new Error(getClass().getSimpleName()+"/addAll", e.getMessage(), new Date()));
            return false;
        }
        return true;
    }

    public boolean clear() {
        try {
            orderHeadRepo.deleteAll();
        }catch (Exception e){
            errorService.add(new Error(getClass().getSimpleName()+"/clear", e.getMessage(), new Date()));
            return false;
        }
        return true;
    }

    public Iterable<OrderHead> giveAllByIdAgent(String idAgent) {
        try {
            return orderHeadRepo.findByIdAgent(idAgent);
        }catch (Exception e){
            errorService.add(new Error(getClass().getSimpleName()+"/giveAllByIdAgent", e.getMessage(), new Date()));
            return null;
        }
    }

    public Iterable<UpdateObj> giveAllByIdDoc(Arrays IdDocs) {
        try {
            return orderHeadRepo.findIdByIdDoc(IdDocs);
        }catch (Exception e){
            errorService.add(new Error(getClass().getSimpleName()+"/giveAllByIdAgent", e.getMessage(), new Date()));
            return null;
        }
    }

    public ArrayList<String> giveListNotUpdateDoc(Iterable<UpdateObj> obs) {

        try {
            HashSet<String> hashSet = new HashSet<>();
//            orderHeadRepo.findIdByIdDoc(IdDocs);
//            for (UpdateObj obj : obs) {
//               if (orderHeadRepo.findByIdDocAndDateUpdateGreaterThan(obj.getIdUpdate(), obj.getDateUpdate()).isPresent()){
//                   hashSet.add(obj.getIdUpdate());
//               }
//            }
            return new ArrayList<>(hashSet);
        }catch (Exception e){
            errorService.add(new Error(getClass().getSimpleName()+"/giveListNotUpdateDoc", e.getMessage(), new Date()));
            return null;
        }

    }

}
