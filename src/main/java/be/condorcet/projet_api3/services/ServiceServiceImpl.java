package be.condorcet.projet_api3.services;
import be.condorcet.projet_api3.repositories.ServiceRepository;
import be.condorcet.projet_api3.modele.Service;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;


import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
@Transactional(rollbackOn = Exception.class)
public class ServiceServiceImpl implements InterfServiceService{
    @Autowired
    private ServiceRepository serviceRepository;

    @Override
    public List<Service> read(String nom) {
        return serviceRepository.findByNomLike(nom+"%");
    }

    @Override
    public Service read(String nom, BigDecimal budget) {
        return serviceRepository.findByNomAndBudget(nom,budget).get(0);
    }

    @Override
    public List<Service> read(BigDecimal budget) {
        return serviceRepository.findByBudget(budget);
    }

    @Override
    public Service create(Service service) throws Exception {
        serviceRepository.save(service);
        return service;
    }

    @Override
    public Service read(Integer id) throws Exception {
        Optional<Service> oserv= serviceRepository.findById(id);
        return oserv.get();
    }

    @Override
    public Service update(Service service) throws Exception {
        read(service.getIdservice());
        serviceRepository.save(service);
        return service;
    }

    @Override
    public void delete(Service service) throws Exception {
        serviceRepository.deleteById(service.getIdservice());
    }

    @Override
    public List<Service> all() throws Exception {
        return serviceRepository.findAll();
    }

}
