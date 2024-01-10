package be.condorcet.projet_api3.repositories;

import be.condorcet.projet_api3.modele.Employe;
import be.condorcet.projet_api3.modele.Message;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
@Repository
public interface MessageRepository extends JpaRepository<Message,Integer> {

    List<Message> findByObjetLike(String objet);

    List<Message> findByObjetAndContenuAndDateenvoi(String objet, String contenu, LocalDate date);

    List<Message> findMessageByEmetteur(Employe emetteur);

    List<Message> findMessagesByDateenvoiIsBetweenAndEmetteur(LocalDate date1, LocalDate date2, Employe emetteur);
}
