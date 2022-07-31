package ua.tarasov.velesBase.service.catalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.tarasov.velesBase.document.catalog.Product;
import ua.tarasov.velesBase.document.extra.Error;
import ua.tarasov.velesBase.repository.doc.catalog.ProductRepo;
import ua.tarasov.velesBase.service.extra.ErrorService;
import ua.tarasov.velesBase.service.extra.ParamService;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private ErrorService errorService;
    @Autowired
    private ParamService paramService;

    public boolean addAll(Iterable<Product> obs) {
        try {
            productRepo.saveAll(obs);
        }catch (Exception e){
            errorService.add(new Error(getClass().getSimpleName()+"/addAll", e.getMessage(), new Date()));
            return false;
        }
        return true;
    }

    public boolean clear() {
        try {
            productRepo.deleteAll();
        }catch (Exception e){
            errorService.add(new Error(getClass().getSimpleName()+"/clear", e.getMessage(), new Date()));
            return false;
        }
        return true;
    }

    public Iterable<Product> giveAll() {
        try {
            return productRepo.findAll();
        }catch (Exception e){
            errorService.add(new Error(getClass().getSimpleName()+"/giveAll", e.getMessage(), new Date()));
            return null;
        }
    }

    public Iterable<Product> giveAllById(String[] value) {
        try {
            return productRepo.findAllById(Arrays.asList(value));
        }catch (Exception e){
            errorService.add(new Error(getClass().getSimpleName()+"/giveAllById", e.getMessage(), new Date()));
            return null;
        }
    }

    public Iterable<Product> giveAllLike(String heritage) {
        try {
            return productRepo.findByHeritageLike(heritage);
        }catch (Exception e){
            errorService.add(new Error(getClass().getSimpleName()+"/giveAllLike", e.getMessage(), new Date()));
            return null;
        }
    }
}
