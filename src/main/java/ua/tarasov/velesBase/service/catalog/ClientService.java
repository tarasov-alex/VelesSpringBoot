package ua.tarasov.velesBase.service.catalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.tarasov.velesBase.document.catalog.Client;
import ua.tarasov.velesBase.document.extra.Error;
import ua.tarasov.velesBase.repository.doc.catalog.ClientRepo;
import ua.tarasov.velesBase.service.extra.ErrorService;

import java.util.Arrays;
import java.util.Date;

@Service
public class ClientService {

    @Autowired
    private ClientRepo clientRepo;
    @Autowired
    private ErrorService errorService;

    public boolean addAll(Iterable<Client> obs) {
        try {
            clientRepo.saveAll(obs);
        }catch (Exception e){
            errorService.add(new Error(getClass().getSimpleName()+"/addAll", e.getMessage(), new Date()));
            return false;
        }
        return true;
    }

    public boolean clear() {
        try {
            clientRepo.deleteAll();
        }catch (Exception e){
            errorService.add(new Error(getClass().getSimpleName()+"/clear", e.getMessage(), new Date()));
            return false;
        }
        return true;
    }

    public Iterable<Client> giveAll() {
        try {
            return clientRepo.findAll();
        }catch (Exception e){
            errorService.add(new Error(getClass().getSimpleName()+"/giveAll", e.getMessage(), new Date()));
            return null;
        }
    }

    public Iterable<Client> giveAllById(String[] value) {
        try {
            return clientRepo.findAllById(Arrays.asList(value));
        }catch (Exception e){
            errorService.add(new Error(getClass().getSimpleName()+"/giveAllById", e.getMessage(), new Date()));
            return null;
        }
    }
}
