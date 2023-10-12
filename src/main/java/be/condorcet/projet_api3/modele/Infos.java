package be.condorcet.projet_api3.modele;

import java.time.LocalDate;
import java.util.Objects;

/**
 * classe métier de gestion des informations des messages
 *
 * @author Romain Vandenplas
 * @version 1.0
 * @see Employe
 */

public class Infos {
    /**
    * date de lecture du message
    */
    private LocalDate dateLecture;

    /**
     * employe qui recoit le message
     */
    private Employe recepteur;

    /**
     * constructeur paramétré
     * @param dateLecture date où le message a été lu
     * @param recepteur employé qui recoit le message
     */
    public Infos(LocalDate dateLecture, Employe recepteur) {
        this.dateLecture = dateLecture;
        this.recepteur = recepteur;
    }

    /**
     * getter DateLecture
     * @return date de lecture du message
     */
    public LocalDate getDateLecture() {
        return dateLecture;
    }

    /**
     * setter DateLecture
     * @param dateLecture date de lecture du message
     */
    public void setDateLecture(LocalDate dateLecture) {
        this.dateLecture = dateLecture;
    }

    /**
     * getter recepteur
     * @return recepteur
     */
    public Employe getRecepteur() {
        return recepteur;
    }

    /**
     *  setter recepteur
     * @param recepteur recepteur
     */
    public void setRecepteur(Employe recepteur) {
        this.recepteur = recepteur;
    }

    /**
     * test d'égalité basé sur la date de lecture et le recepteur
     * @param o autre objet info
     * @return égalité ou pas
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Infos infos = (Infos) o;
        return Objects.equals(dateLecture, infos.dateLecture) && Objects.equals(recepteur, infos.recepteur);
    }

    /**
     * hashcode basé sur la date de lecture,le message et le recepteur
     * @return hashcode des informations
     */
    @Override
    public int hashCode() {
        return Objects.hash(dateLecture, recepteur);
    }
}
