package be.condorcet.projet_api3.services;

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
class ServiceServiceImplTest {

    @Autowired
    private InterfServiceService serviceServiceImpl;
    Service serv;

    @BeforeEach
    void setUp() {
        try {
            serv = new Service(null, "testNom", new BigDecimal(1000));
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
    void read() {
        int numserv = serv.getId_service();
        try {
            Service s = serviceServiceImpl.read(numserv);
            assertEquals(serv, s, "La recherche n'a pas envoyer le bon serice");
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
            assertEquals(serv, ls.get(0), "La recherche n'a pas envoyer le bon employe");
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
    void all() {
        try {
            List<Service> lemp = serviceServiceImpl.all();
            assertNotEquals(0, lemp.size(), "la liste ne contient aucun élément");

        } catch (Exception e) {
            fail("erreur de recherche de tous les employes " + e);
        }

    }
}