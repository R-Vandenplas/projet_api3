package be.condorcet.projet_api3;

import be.condorcet.projet_api3.modele.Employe;
import be.condorcet.projet_api3.repositories.EmployeRepository;
import be.condorcet.projet_api3.services.InterfEmployeService;
import be.condorcet.projet_api3.services.InterfService;
import be.condorcet.projet_api3.services.InterfServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/employe")
public class GestEmploye {
    @Autowired
    InterfEmployeService employeServiceImpl;
    @Autowired
    InterfServiceService serviceServiceImpl;
    @RequestMapping("/tous")
    public String affTous(Map<String, Object> model) {
        System.out.println("recherche employe");
        List<Employe> liste;
        try {
            liste = employeServiceImpl.all();
            model.put("mesEmployes", liste);
        } catch (Exception e) {
            System.out.println("----------erreur lors de la recherche-------- " + e);
            return "error";
        }
        return "affichageTousEmployes";

    }
    @RequestMapping("/create")
    public String create(@RequestParam String nom,@RequestParam String prenom,@RequestParam String mail,@RequestParam int numServ, Map<String, Object> model){
        System.out.println("création de client");
        try {
            Employe emp = new Employe(nom,prenom,mail,serviceServiceImpl.read(numServ));
            employeServiceImpl.create(emp);
            model.put("nouvemp",emp);
        } catch (Exception e) {
            System.out.println("----------erreur lors de la création-------- " + e);
            model.put("error",e.getMessage());
            return "error";
        }
        return "nouveauEmploye";
    }
    @RequestMapping("/selection")
    String read(@RequestParam int id_employe, Map<String, Object> model) {
        System.out.println("recherche de l'employe n° "+id_employe);
        try {
            Employe em = employeServiceImpl.read(id_employe);
            model.put("monEmploye", em);

        } catch (Exception e) {
            System.out.println("erreur lors de la lecture " + e);
            model.put("error",e);
            return "error";
        }
        return "affichageEmploye";
    }
}
