package be.condorcet.projet_api3.webservices;

import be.condorcet.projet_api3.modele.Employe;
import be.condorcet.projet_api3.modele.Message;
import be.condorcet.projet_api3.services.InterfEmployeService;
import be.condorcet.projet_api3.services.InterfMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/message")
public class RestMessage {
    @Autowired
    private InterfMessageService messageServiceImpl;
    @Autowired
    private InterfEmployeService employeServiceImpl;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Message> getMessage(@PathVariable(value = "id") int id)  throws Exception{
        System.out.println("recherche du message n° " + id);
        Message mes = messageServiceImpl.read(id);
        return new ResponseEntity<>(mes, HttpStatus.OK);
    }

    @RequestMapping(value = "/idemploye={id}", method = RequestMethod.GET)
    public ResponseEntity<List<Message>> getMessageEmploye(@PathVariable(value = "id") int id)  throws Exception{
        System.out.println("recherche des messages de l'employe d'id " + id);
        Employe emp = employeServiceImpl.read(id);
        List<Message> lmes = messageServiceImpl.getMessages(emp);
        return new ResponseEntity<>(lmes, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Message> createMessage(@RequestBody Message mes) throws Exception {
        System.out.println("Création du message de l'employe " + mes.getEmetteur());
        messageServiceImpl.create(mes);
        return new ResponseEntity<>(mes, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Message> majMessage(@PathVariable(value = "id") int id,@RequestBody Message nouvmes) throws Exception{
        System.out.println("maj du message n° " + id);
        nouvmes.setId_message(id);
        Message messact = messageServiceImpl.update(nouvmes);
        return new ResponseEntity<>(messact, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteMessage(@PathVariable(value = "id") int id) throws Exception{
        System.out.println("effacement du message n°" + id);
        Message mes = messageServiceImpl.read(id);
        messageServiceImpl.delete(mes);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value =  "/all",method = RequestMethod.GET)
    public ResponseEntity<List<Message>> listMessage() throws Exception{
        System.out.println("recherche de toutes les messages");
        return new ResponseEntity<>(messageServiceImpl.all(), HttpStatus.OK);
    }


    @ExceptionHandler({Exception.class})
    public ResponseEntity<Void>  handleIOException(Exception ex) {
        System.out.println("erreur : "+ex.getMessage());
        return ResponseEntity.notFound().header("error",ex.getMessage()).build();
    }
}

