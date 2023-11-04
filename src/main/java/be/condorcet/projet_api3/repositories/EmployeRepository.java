package be.condorcet.projet_api3.repositories;

import be.condorcet.projet_api3.modele.Employe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeRepository extends JpaRepository<Employe,Integer> {

    public List<Employe> findByNomLike(String nom);
    public List<Employe> findByNomAndPrenomAndMail(String nom,String prenom,String mail);

}
