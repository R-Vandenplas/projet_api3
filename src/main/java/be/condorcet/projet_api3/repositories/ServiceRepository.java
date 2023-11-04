package be.condorcet.projet_api3.repositories;

import be.condorcet.projet_api3.modele.Service;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface ServiceRepository extends JpaRepository<Service,Integer> {

    List<Service> findByNomLike(String nom);

    List<Service> findByNomAndBudget(String nom, BigDecimal budget);
    List<Service> findByBudget(BigDecimal budget);

}