package be.condorcet.projet_api3.webservices;


import be.condorcet.projet_api3.modele.Employe;
import be.condorcet.projet_api3.modele.Service;
import be.condorcet.projet_api3.services.InterfEmployeService;
import be.condorcet.projet_api3.services.InterfServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*", allowedHeaders = "*",exposedHeaders = "*")
@RestController
@RequestMapping("/service")
public class RestService {
    @Autowired
    private InterfServiceService serviceServiceImpl;

    @Autowired
    private InterfEmployeService employeServiceImpl;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Service> getService(@PathVariable(value = "id") int id)  throws Exception{
        System.out.println("recherche du service n° " + id);
        Service serv = serviceServiceImpl.read(id);
        return new ResponseEntity<>(serv, HttpStatus.OK);
    }


    @RequestMapping(value = "/nom={nom}", method = RequestMethod.GET)
    public ResponseEntity<List<Service>> getServiceByNom(@PathVariable(value = "nom") String nom)  throws Exception{
        System.out.println("recherche du service nommé " + nom);
        List<Service> lserv = serviceServiceImpl.read(nom);
        return new ResponseEntity<>(lserv, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Service> createMessage(@RequestBody Service serv) throws Exception {
        System.out.println("Création du service " + serv.getId_service());
        serviceServiceImpl.create(serv);
        return new ResponseEntity<>(serv, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Service> majService(@PathVariable(value = "id") int id,@RequestBody Service nouvserv) throws Exception{
        System.out.println("maj du service n° " + id);
        nouvserv.setId_service(id);
        Service servact = serviceServiceImpl.update(nouvserv);
        return new ResponseEntity<>(servact, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteService(@PathVariable(value = "id") int id) throws Exception{
        System.out.println("effacement du service n°" + id);
        Service serv = serviceServiceImpl.read(id);
        serviceServiceImpl.delete(serv);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value =  "/all",method = RequestMethod.GET)
    public ResponseEntity<List<Service>> listService() throws Exception{
        System.out.println("recherche de toutes les services");
        return new ResponseEntity<>(serviceServiceImpl.all(), HttpStatus.OK);
    }


    @ExceptionHandler({Exception.class})
    public ResponseEntity<Void>  handleIOException(Exception ex) {
        System.out.println("erreur : "+ex.getMessage());
        return ResponseEntity.notFound().header("error",ex.getMessage()).build();
    }
}
