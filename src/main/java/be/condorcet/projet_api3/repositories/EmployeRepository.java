package be.condorcet.projet_api3.repositories;

import be.condorcet.projet_api3.modele.Employe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeRepository extends JpaRepository<Employe,Integer> {
    @Override
    List<Employe> findAll();

    @Override
    Optional<Employe> findById(Integer integer);
}
