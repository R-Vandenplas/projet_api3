package be.condorcet.projet_api3.services;
import be.condorcet.projet_api3.repositories.EmployeRepository;
import be.condorcet.projet_api3.modele.Employe;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackOn = Exception.class)
public class EmployeServiceImpl implements InterfEmployeService{
    @Autowired
    private EmployeRepository employeRepository;

    @Override
    public List<Employe> read(String nom) {
        return employeRepository.findByNomLike(nom+"%");
    }

    @Override
    public Employe read(String nom, String prenom, String mail) {
        return employeRepository.findByNomAndPrenomAndMail(nom,prenom,mail).get(0);
    }

    @Override
    public Employe create(Employe employe) throws Exception {
        employeRepository.save(employe);
        return employe;
    }

    @Override
    public Employe read(Integer id) throws Exception {
        Optional<Employe> oem= employeRepository.findById(id);
        return oem.get();
    }

    @Override
    public Employe update(Employe employe) throws Exception {
        read(employe.getIdemploye());
       employeRepository.save(employe);
        return employe;
    }

    @Override
    public void delete(Employe employe) throws Exception {
        employeRepository.deleteById(employe.getIdemploye());
    }

    @Override
    public List<Employe> all() throws Exception {
        return employeRepository.findAll();
    }

}
