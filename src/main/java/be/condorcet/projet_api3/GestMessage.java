package be.condorcet.projet_api3;


import be.condorcet.projet_api3.modele.Message;
import be.condorcet.projet_api3.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/message")
public class GestMessage {
    @Autowired     //instanciation "automatique" par le framework avec les paramètres indiqués, il s'agit d'un singleton
    MessageRepository messageRepository;

    @RequestMapping("/tous")
    public String affTous(Map<String, Object> model) {
        System.out.println("recherche message");
        List<Message> liste;
        try {
            liste = messageRepository.findAll();
            model.put("mesMessages", liste);
        } catch (Exception e) {
            System.out.println("----------erreur lors de la recherche-------- " + e);
            return "error";
        }
        return "affichagetousMessages";

    }

    @RequestMapping("/selection")
    String selection(@RequestParam("nummes") int nummes, Map<String, Object> model) {
        Message mes = null;
        Optional<Message> omes;
        try {
            omes = messageRepository.findById(nummes);
            if(omes.isPresent()) mes=omes.get();
            else throw new Exception("message inconnu");
            model.put("monEmploye", mes);

        } catch (Exception e) {
            System.out.println("erreur lors de la lecture " + e);
            model.put("error",e);
            return "error";
        }
        return "affichageMessage";
    }
}
