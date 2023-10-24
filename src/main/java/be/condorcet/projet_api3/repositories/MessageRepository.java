package be.condorcet.projet_api3.repositories;

import be.condorcet.projet_api3.modele.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message,Integer> {
    @Override
    List<Message> findAll();
}
