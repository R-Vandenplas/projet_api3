package be.condorcet.projet_api3.services;

import be.condorcet.projet_api3.modele.Employe;
import be.condorcet.projet_api3.modele.Message;

import java.time.LocalDate;
import java.util.List;

public interface InterfMessageService extends InterfService<Message>{
    public List<Message> read(String objet);

    Message read(String objet, String contenu, LocalDate date);

    List<Message> getMessages(Employe emp);

    List<Message> getMessages(Employe emp, LocalDate date1, LocalDate date2);
}
