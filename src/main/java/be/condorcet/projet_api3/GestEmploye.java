package be.condorcet.projet_api3;

import be.condorcet.projet_api3.modele.Employe;
import be.condorcet.projet_api3.repositories.EmployeRepository;
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
    @Autowired     //instanciation "automatique" par le framework avec les paramètres indiqués, il s'agit d'un singleton
    EmployeRepository employeRepository;

    @RequestMapping("/tous")
    public String affTous(Map<String, Object> model) {
        System.out.println("recherche employe");
        List<Employe> liste;
        try {
            liste = employeRepository.findAll();
            model.put("mesEmployes", liste);
        } catch (Exception e) {
            System.out.println("----------erreur lors de la recherche-------- " + e);
            return "error";
        }
        return "affichageTousEmployes";

    }

    @RequestMapping("/selection")
    String selection(@RequestParam("numemp") int numemp, Map<String, Object> model) {
        Employe em = null;
        Optional<Employe> oem;
        try {
            oem = employeRepository.findById(numemp);
            if(oem.isPresent()) em=oem.get();
            else throw new Exception("employe inconnu");
            model.put("monEmploye", em);

        } catch (Exception e) {
            System.out.println("erreur lors de la lecture " + e);
            model.put("error",e);
            return "error";
        }
        return "affichageEmploye";
    }
}
