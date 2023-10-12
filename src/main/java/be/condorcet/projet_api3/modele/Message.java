package be.condorcet.projet_api3.modele;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * classe metier de gestion des messages
 * @author Romain Vandenplas
 * @version 1.0
 * @see Employe
 */
public class Message {
    /**
     * identifiant du message
     */
    private int id;
    /**
     * objet du message
     */
    private String objet;
    /**
     * contenu du message
     */
    private String contenu;
    /**
     * date d' envoie du message
     */
    private LocalDate dateEnvoie;
    /**
     * employe qui envoie le message
     */
    private Employe emetteur;
    /**
     * liste des infos du message
     */
    List<Infos> infosList = new ArrayList<>();

    /**
     * constructeur paramétré
     * @param id identifiant
     * @param objet objet du message
     * @param contenu contenu du message
     * @param dateEnvoie date d' envoie du message
     * @param emetteur employe qui envoie le message
     */
    public Message(int id, String objet, String contenu, LocalDate dateEnvoie, Employe emetteur) {
        this.id = id;
        this.objet = objet;
        this.contenu = contenu;
        this.dateEnvoie = dateEnvoie;
        this.emetteur = emetteur;
    }

    /**
     * getter id
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * setter id
     * @param id identifiant
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * getter objet
     * @return objet
     */
    public String getObjet() {
        return objet;
    }

    /**
     * setter objet
     * @param objet objet
     */
    public void setObjet(String objet) {
        this.objet = objet;
    }

    /**
     * getter contenu
     * @return contenu
     */
    public String getContenu() {
        return contenu;
    }

    /**
     * setter contenu
     * @param contenu contenu
     */
    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    /**
     * getter date d'envoie
     * @return date d'envoie
     */
    public LocalDate getDateEnvoie() {
        return dateEnvoie;
    }

    /**
     * setter date d'envoie
     * @param dateEnvoie date d'envoie
     */
    public void setDateEnvoie(LocalDate dateEnvoie) {
        this.dateEnvoie = dateEnvoie;
    }

    /**
     * getter employe emetteur
     * @return employe emetteur
     */
    public Employe getEmetteur() {
        return emetteur;
    }

    /**
     * setter employe emetteur
     * @param emetteur employe emetteur
     */
    public void setEmetteur(Employe emetteur) {
        this.emetteur = emetteur;
    }

    /**
     * test d'égalité basé sur l'id
     * @param o autre message
     * @return égalité ou pas
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return id == message.id;
    }

    /**
     * hashcode basé sur l'id
     * @return hashcode du message
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", objet='" + objet + '\'' +
                ", contenu='" + contenu + '\'' +
                ", dateEnvoie=" + dateEnvoie +
                ", emetteur=" + emetteur +
                '}';
    }
}
