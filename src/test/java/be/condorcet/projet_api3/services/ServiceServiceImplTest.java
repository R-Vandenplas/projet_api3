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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ServiceServiceImplTest {

    @Autowired
    private InterfServiceService serviceServiceImpl;
    @Autowired
    private InterfEmployeService employeServiceImpl;
    Service serv;
    Employe emp;

    @BeforeEach
    void setUp() {
        try {
            serv = new Service(null, "testNom", new BigDecimal(1000),new ArrayList<>());
            serviceServiceImpl.create(serv);
            System.out.println("création du service : " + serv);
        } catch (Exception e) {
            System.out.println("erreur de création du service : " + serv + " erreur : " + e);
        }

    }

    @AfterEach
    void tearDown() {
        try {
            serviceServiceImpl.delete(serv);
            System.out.println("Effacement du service "+serv);
        } catch (Exception e) {
            System.out.println("erreur d'effacement du service " + e);
        }
    }
    @Test
    void doublon(){
        Service s = new Service(null, "testNom", new BigDecimal(1000),new ArrayList<>());
        Assertions.assertThrows(Exception.class, () -> {
            serviceServiceImpl.create(s);
        },"service aj alors que déjà present");

    }

    @Test
    void read() {
        int numserv = serv.getId_service();
        try {
            Service s = serviceServiceImpl.read(numserv);
            assertEquals(numserv, s.getId_service(), "id différent");
            assertEquals("testNom", s.getNom(), "nom service non enregistré : " + s.getNom() + " au lieu de testNom");
            assertEquals(new BigDecimal(1000), s.getBudget(), "budget service non enregistré : " + s.getBudget() + " au lieu de 1000");
        } catch (Exception e) {
            fail("recherche infructueuse " + e);
        }

    }

    @Test
    void testRead() {
        String nomserv = serv.getNom();
        try {
            List<Service> ls = serviceServiceImpl.read(nomserv);
            assertEquals(serv.getNom(), ls.get(0).getNom(), "noms différents " + serv.getNom() + " - " + ls.get(0).getNom());
        } catch (Exception e) {
            fail("recherche infructueuse " + e);
        }
    }

    @Test
    void create() {
        assertNotEquals(0, serv.getId_service(), "id service non incrémenté");
        assertEquals("testNom", serv.getNom(), "nom service non enregistré : " + serv.getNom() + " au lieu de testNom");
        assertEquals(new BigDecimal(1000), serv.getBudget(), "budget service non enregistré : " + serv.getBudget() + " au lieu de 1000");

    }

    @Test
    void testRead1() {
        BigDecimal budget = serv.getBudget();
        try {
            List<Service> ls = serviceServiceImpl.read(budget);
            assertEquals("testNom", ls.get(0).getNom(), "nom service non enregistré : " + ls.get(0).getNom() + " au lieu de testNom");
            assertEquals(new BigDecimal(1000), ls.get(0).getBudget(), "budget service non enregistré : " + ls.get(0).getBudget() + " au lieu de 1000");
        } catch (Exception e) {
            fail("recherche infructueuse " + e);
        }
    }

    @Test
    void update() {
        try {
            serv.setNom("testNom2");
            serv.setBudget(new BigDecimal(2000));
            serv = serviceServiceImpl.update(serv);
            assertEquals("testNom2", serv.getNom(), "nom service non enregistré : " + serv.getNom() + " au lieu de testNom2");
            assertEquals(new BigDecimal(2000), serv.getBudget(), "budget service non enregistré : " + serv.getBudget() + " au lieu de 2000");
        } catch (Exception e) {
            fail("erreur de mise à jour " + e);
        }
    }

    @Test
    void delete() {
        try {
            serviceServiceImpl.delete(serv);
            assertThrows(Exception.class, () -> {
                serviceServiceImpl.read(serv.getId_service());
            }, "record non effacé");
        } catch (Exception e) {
            fail("erreur d'effacement " + e);
        }
    }

    @Test
    void deleteAvecEmploye(){
        try{
            emp= new Employe(null,"testMail","testNom","testPrenom",serv,new ArrayList<>());
            System.out.println("Creation de l'employe : "+emp);
            employeServiceImpl.create(emp);
            serv.getEmployes().add(emp);
            serviceServiceImpl.update(serv);
            assertThrows(Exception.class, () -> {
                System.out.println("suppression du service avec un employé");
                serviceServiceImpl.delete(serv);
            },"effacement réalisé malgré employe liée");
            System.out.println("suppression de l'employe");
            employeServiceImpl.delete(emp);
        }catch (Exception e){
            fail("erreur d'effacement "+e);
        }
    }

    @Test
    void all() {
        try {
            List<Service> lemp = serviceServiceImpl.all();
            assertNotEquals(0, lemp.size(), "la liste ne contient aucun élément");

        } catch (Exception e) {
            fail("erreur de recherche de tous les employes " + e);
        }

    }
}