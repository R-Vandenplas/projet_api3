package be.condorcet.projet_api3;


import be.condorcet.projet_api3.modele.Employe;
import be.condorcet.projet_api3.modele.Message;
import be.condorcet.projet_api3.repositories.MessageRepository;
import be.condorcet.projet_api3.services.InterfEmployeService;
import be.condorcet.projet_api3.services.InterfMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/message")
public class GestMessage {
    @Autowired
    InterfMessageService messageServiceImpl;
    @Autowired
    InterfEmployeService employeServiceImpl;
    @RequestMapping("/tous")
    public String affTous(Map<String, Object> model) {
        System.out.println("recherche message");
        List<Message> liste;
        try {
            liste = messageServiceImpl.all();
            model.put("mesMessages", liste);
        } catch (Exception e) {
            System.out.println("----------erreur lors de la recherche-------- " + e);
            return "error";
        }
        return "affichageTousMessages";

    }

    @RequestMapping("/recherchearId")
    String read(@RequestParam int id_message, Map<String, Object> model) {
        try {
            Message mes = messageServiceImpl.read(id_message);
            model.put("monEmploye", mes);

        } catch (Exception e) {
            System.out.println("erreur lors de la lecture " + e);
            model.put("error",e);
            return "error";
        }
        return "affichageMessage";
    }
    @RequestMapping("/rechercheparemp")
    public String readEmp(@RequestParam int id_emp, Map<String, Object> model) {
        System.out.println("recherche des messages de l'employe n° " + id_emp);
        try {
            Employe emp = employeServiceImpl.read(id_emp);
            List<Message> lmes = messageServiceImpl.getMessages(emp);
            model.put("monemp",emp);
            model.put("mespemp", lmes);
        } catch (Exception e) {
            System.out.println("----------erreur lors de la recherche -------- " + e);
            model.put("error", e.getMessage());
            return "error";
        }
        return "affichageMessageParEmploye";

    }
}
