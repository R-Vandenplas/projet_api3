package be.condorcet.projet_api3.services;

import be.condorcet.projet_api3.modele.Employe;
import be.condorcet.projet_api3.modele.Message;
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
class MessageServiceImplTest {
    @Autowired
    private InterfEmployeService employeServiceImpl;
    @Autowired
    private InterfServiceService serviceServiceImpl;
    @Autowired
    private InterfMessageService messageServiceImpl;
    Employe emp;
    Service serv ;
    Message mes;

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
        try {
            mes= new Message(null,"testObjet","testContenu",LocalDate.of(01,01,01),emp);
            System.out.println("Creation du message : "+mes);
            messageServiceImpl.create(mes);

        }catch (Exception e){
            System.out.println("Erreur de la création du message : "+mes+ " erreur : "+e);
        }
    }

    @AfterEach
    void tearDown() {
        try {
            messageServiceImpl.delete(mes);
            System.out.println("Effacement du message : "+mes);
        }catch (Exception e){
            System.out.println("Erreur de la suppression du message : "+mes+ " erreur : "+e);
        }
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
        int nummes= mes.getIdmessage();
        try{
            Message m= messageServiceImpl.read(nummes);
            assertEquals(nummes,m.getIdmessage(),"id different");
            assertEquals("testObjet",m.getObjet(),"objet message non enregistré : "+m.getObjet()+ " au lieu de testObjet");
            assertEquals("testContenu",m.getContenu(),"contenu message non enregistré : "+m.getContenu()+" au lieu de testContenu");
            assertEquals(LocalDate.of(01,01,01),m.getDateenvoi(),"date du message non enregistré : "+ m.getDateenvoi()+"au lieu de "+LocalDate.of(01,01,01));
            assertEquals("testNom",m.getEmetteur().getNom(),"nom employe non enregistré : "+m.getEmetteur().getNom()+ " au lieu de testNom");
        }catch (Exception e){
            fail("recherche infructueuse "+e);
        }
    }

    @Test
    void testRead() {
        String objet = mes.getObjet();
        try {
            List<Message> lm =messageServiceImpl.read(objet);
            assertNotEquals(0,lm.size(),"La liste est vide");
            assertEquals(mes.getObjet(),lm.get(0).getObjet(),"objet message: "+mes.getObjet()+ " au lieu de testObjet");

        }catch (Exception e){
            fail("recherche infructueuse "+e);
        }
    }

    @Test
    void create() {
        assertNotEquals(0,mes.getIdmessage(),"id message non incrémenté");
        assertEquals("testObjet",mes.getObjet(),"objet message non enregistré : "+mes.getObjet()+ " au lieu de testObjet");
        assertEquals("testContenu",mes.getContenu(),"contenu message non enregistré : "+mes.getContenu()+" au lieu de testContenu");
        assertEquals(LocalDate.of(01,01,01),mes.getDateenvoi(),"date du message non enregistré : "+ mes.getDateenvoi()+"au lieu de "+LocalDate.of(01,01,01));
        assertEquals("testNom",mes.getEmetteur().getNom(),"nom employe non enregistré : "+mes.getEmetteur().getNom()+ " au lieu de testNom");
    }
    @Test
    void doublon(){
        Message db = new Message(null,"testObjet","testContenu",LocalDate.of(01,01,01),emp);
        Assertions.assertThrows(Exception.class, () -> {
            messageServiceImpl.create(db);
        },"employe ajouté alors que déjà present");
    }

    @Test
    void testRead1() {
        String objet = mes.getObjet();
        String contenu = mes.getContenu();
        LocalDate date = mes.getDateenvoi();
        try {
            Message m = messageServiceImpl.read(objet,contenu,date);
            assertEquals("testObjet",m.getObjet(),"objet message non enregistré : "+m.getObjet()+ " au lieu de testObjet");
            assertEquals("testContenu",m.getContenu(),"contenu message non enregistré : "+m.getContenu()+" au lieu de testContenu");
            assertEquals(LocalDate.of(01,01,01),m.getDateenvoi(),"date du message non enregistré : "+ m.getDateenvoi()+"au lieu de "+LocalDate.of(01,01,01));
            assertEquals("testNom",m.getEmetteur().getNom(),"nom employe non enregistré : "+m.getEmetteur().getNom()+ " au lieu de testNom");

        }catch (Exception e){
            fail("recherche infructueuse "+e);
        }

    }

    @Test
    void update() {

        try {
            mes.setContenu("testContenu2");
            mes.setObjet("testObjet2");
            mes.setDateenvoi(LocalDate.of(02, 02, 02));
            mes = messageServiceImpl.update(mes);
            assertEquals("testObjet2", mes.getObjet(), "objet message non enregistré : " + mes.getObjet() + " au lieu de testObjet2");
            assertEquals("testContenu2", mes.getContenu(), "contenu message non enregistré : " + mes.getContenu() + " au lieu de testContenu2");
            assertEquals(LocalDate.of(02, 02, 02), mes.getDateenvoi(), "date du message non enregistré : " + mes.getDateenvoi() + "au lieu de " + LocalDate.of(02, 02, 02));

        } catch (Exception e) {
            fail("erreur de mise à jour " + e);
        }
    }


    @Test
    void delete() {
        try {
            messageServiceImpl.delete(mes);
            Assertions.assertThrows(Exception.class, () -> {
                messageServiceImpl.read(mes.getIdmessage());
            },"record non effacé");
        }catch (Exception e){
            fail("erreur d'effacement "+e);
        }
    }

    @Test
    void all() {
        try {
            List<Message> lm = messageServiceImpl.all();
            assertNotEquals(0,lm.size(),"la liste ne contient aucun élément");

        }catch (Exception e){
            fail("erreur de recherche de tous les employes "+e);
        }
    }

    @Test
    void getMessages() {
        Employe e = mes.getEmetteur();
        try {
            List<Message> lm = messageServiceImpl.getMessages(e);
            assertNotEquals(0,lm.size(),"la liste ne contient aucun élément");
            assertEquals("testNom",lm.get(0).getEmetteur().getNom(),"nom employe non enregistré : "+lm.get(0).getEmetteur().getNom()+ " au lieu de testNom");
        }catch (Exception ex){
        fail("erreur dans la liste des messages par employe "+ex);
    }
    }
}