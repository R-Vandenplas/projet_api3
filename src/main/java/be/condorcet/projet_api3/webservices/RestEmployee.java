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
@RequestMapping("/employe")
public class RestEmployee {
    @Autowired
    private InterfEmployeService employeServiceImpl;
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Employe> getEmploye(@PathVariable(value = "id") int id)  throws Exception{
        System.out.println("recherche de l'employe d' id " + id);
        Employe employe = employeServiceImpl.read(id);
        return new ResponseEntity<>(employe, HttpStatus.OK);
    }

    @RequestMapping(value = "/nom={nom}", method = RequestMethod.GET)
    public ResponseEntity<List<Employe>> listEmployesNom(@PathVariable(value="nom") String nom) throws Exception{
        System.out.println("recherche de "+nom);
        List<Employe> employes;
        employes = employeServiceImpl.read(nom);
        return new ResponseEntity<>(employes, HttpStatus.OK);
    }
    @RequestMapping(value = "/{nom}/{prenom}/{mail}", method = RequestMethod.GET)
    public ResponseEntity<Employe> getEmployeUnique(@PathVariable(value = "nom") String nom,
                                                  @PathVariable(value = "prenom") String prenom,
                                                  @PathVariable(value = "mail") String mail)  throws Exception{
        System.out.println("recherche de l'employe "+nom+" "+prenom+" "+mail);
        Employe employe = employeServiceImpl.read(nom,prenom,mail);
        return new ResponseEntity<>(employe, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Employe> createEmploye(@RequestBody Employe employe) throws Exception {
        System.out.println("Création de l'employe " + employe.getNom());
        employeServiceImpl.create(employe);
        return new ResponseEntity<>(employe, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Employe> majEmploye(@PathVariable(value = "id") int id,@RequestBody Employe nouvemp) throws Exception{
        System.out.println("maj de l'employe id =  " + id);
        nouvemp.setIdemploye(id);
        Employe empact = employeServiceImpl.update(nouvemp);
        return new ResponseEntity<>(empact, HttpStatus.OK);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteEmploye(@PathVariable(value = "id") int id) throws Exception{
        System.out.println("effacement de l'employe d'id " + id);
        Employe employe = employeServiceImpl.read(id);
        employeServiceImpl.delete(employe);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value =  "/all",method = RequestMethod.GET)
    public ResponseEntity<List<Employe>> listEmploye() throws Exception{
        System.out.println("recherche de tous les employes");
        return new ResponseEntity<>(employeServiceImpl.all(), HttpStatus.OK);
    }
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Void>  handleIOException(Exception ex) {
        System.out.println("erreur : "+ex.getMessage());
        return ResponseEntity.notFound().header("error",ex.getMessage()).build();
    }

}
