package be.condorcet.projet_api3.services;

import be.condorcet.projet_api3.modele.Employe;

import java.util.List;

public interface InterfEmployeService extends InterfService<Employe> {

    public List<Employe> read(String nom);

    Employe read(String nom, String prenom, String mail);
}
