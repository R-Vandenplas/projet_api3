package be.condorcet.projet_api3.services;

import be.condorcet.projet_api3.modele.Employe;
import be.condorcet.projet_api3.modele.Service;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class EmployeServiceImplTest {

    @Autowired
    private InterfEmployeService employeServiceImpl;
    @Autowired
    private InterfServiceService serviceServiceImpl;
    Employe emp;
    Service serv ;
    @BeforeEach
    void setUp() {
        try{
            serv = new Service(null,"testService", new BigDecimal(1000),new ArrayList<>());
            serviceServiceImpl.create(serv);
            System.out.println("création du service : "+serv);
        }catch (Exception e){
            System.out.println("erreur de création du service : "+serv+ " erreur : "+e);
        }
        try{

            emp = new Employe(null,"testMail","testNom","testPrenom",serv,new ArrayList<>());
            System.out.println("Creation de l'employe : "+emp);
            employeServiceImpl.create(emp);

        }catch (Exception e){
            System.out.println("erreur de création de l'employe : "+emp+ " erreur : "+e);
        }

    }

    @AfterEach
    void tearDown() {
        try {
            employeServiceImpl.delete(emp);
            System.out.println("effacement de l'employe : "+emp);
        }
        catch (Exception e){
            System.out.println("erreur d'effacement de l'employe :"+emp+" erreur : "+e);
        }
        try{
            serviceServiceImpl.delete(serv);
            System.out.println("effacement du service : "+serv);
        }
        catch(Exception e){
            System.out.println("erreur d'effacement du service "+e);
        }
    }

    @Test
    void read() {
        int numemp= emp.getIdemploye();
        try{
           Employe e= employeServiceImpl.read(numemp);
           assertEquals("testNom",e.getNom(),"nom employe non enregistré : "+e.getNom()+ " au lieu de testNom");
           assertEquals("testPrenom",e.getPrenom(),"prénom employe non enregistré : "+e.getPrenom()+" au lieu de testPrenom");
           assertEquals("testMail",e.getMail(),"mail enploye non enregistré : "+e.getMail()+" au lieu de testMail");
        }catch (Exception e){
            fail("recherche infructueuse "+e);
        }

    }

    @Test
    void testRead() {
        String nomemp = emp.getNom();
        try{
            List<Employe> le= employeServiceImpl.read(nomemp);
            assertNotEquals(0,le.size(),"La liste est vide");
            assertEquals("testNom",le.get(0).getNom(),"nom employe non enregistré : "+emp.getNom()+ " au lieu de testNom");
        }catch (Exception e){
            fail("recherche infructueuse "+e);
        }
    }

    @Test
    void create() {
        assertNotEquals(0,emp.getIdemploye(),"id employe non incrémenté");
        assertEquals("testNom",emp.getNom(),"nom employe non enregistré : "+emp.getNom()+ " au lieu de testNom");
        assertEquals("testPrenom",emp.getPrenom(),"prénom employe non enregistré : "+emp.getPrenom()+" au lieu de testPrenom");
        assertEquals("testMail",emp.getMail(),"mail enploye non enregistré : "+emp.getMail()+" au lieu de testMail");
    }

    @Test
    void testRead1() {
        String mail= emp.getMail();
        String nom= emp.getNom();
        String prenom = emp.getPrenom();
        try{
            Employe e= employeServiceImpl.read(nom,prenom,mail);
            assertEquals("testNom",e.getNom(),"nom employe non enregistré : "+e.getNom()+ " au lieu de testNom");
            assertEquals("testPrenom",e.getPrenom(),"prénom employe non enregistré : "+e.getPrenom()+" au lieu de testPrenom");
            assertEquals("testMail",e.getMail(),"mail enploye non enregistré : "+e.getMail()+" au lieu de testMail");
        }catch (Exception ex){
            fail("recherche infructueuse "+ex);
        }
    }

    @Test
    void update() {
        try{
            emp.setNom("testNom2");
            emp.setPrenom("testPrenom2");
            emp.setMail("testMail2");
            emp= employeServiceImpl.update(emp);
            assertEquals("testNom2",emp.getNom(),"nom employe non enregistré : "+emp.getNom()+ " au lieu de testNom2");
            assertEquals("testPrenom2",emp.getPrenom(),"prénom employe non enregistré : "+emp.getPrenom()+" au lieu de testPrenom2");
            assertEquals("testMail2",emp.getMail(),"mail enploye non enregistré : "+emp.getMail()+" au lieu de testMail2");
        }catch (Exception e){
            fail("erreur de mise à jour "+e);
        }
    }

    @Test
    void delete() {
        try{
            employeServiceImpl.delete(emp);
            Assertions.assertThrows(Exception.class, () -> {
                employeServiceImpl.read(emp.getIdemploye());
            },"record non effacé");
        }catch (Exception e){
            fail("erreur d'effacement "+e);
        }
    }

    @Test
    void all() {
        try {
            List<Employe> lemp = employeServiceImpl.all();
            assertNotEquals(0,lemp.size(),"la liste ne contient aucun élément");

        }catch (Exception e){
            fail("erreur de recherche de tous les employes "+e);
        }
    }
}