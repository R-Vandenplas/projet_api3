package be.condorcet.projet_api3.services;

import be.condorcet.projet_api3.modele.Employe;
import be.condorcet.projet_api3.repositories.EmployeRepository;
import be.condorcet.projet_api3.repositories.MessageRepository;
import be.condorcet.projet_api3.modele.Message;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackOn = Exception.class)
public class MessageServiceImpl implements InterfMessageService{
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private EmployeRepository employeRepository;
    @Override
    public List<Message> read(String objet) {
        return messageRepository.findByObjetLike(objet+"%");
    }

    @Override
    public Message read(String objet, String contenu, LocalDate date) {
        return messageRepository.findByObjetAndContenuAndDateenvoi(objet,contenu,date).get(0);
    }

    @Override
    public Message create(Message message) throws Exception {
        messageRepository.save(message);
        return message;
    }

    @Override
    public Message read(Integer id) throws Exception {
        Optional<Message> omes= messageRepository.findById(id);
        return omes.get();
    }

    @Override
    public Message update(Message message) throws Exception {
        read(message.getIdmessage());
        messageRepository.save(message);
        return message;
    }

    @Override
    public void delete(Message message) throws Exception {
        messageRepository.deleteById(message.getIdmessage());
    }

    @Override
    public List<Message> all() throws Exception {
        return messageRepository.findAll();
    }
    @Override
    public List<Message> getMessages(Employe emp) {
        List<Message> lemp = messageRepository.findMessageByEmetteur(emp);
        return lemp;
    }
    @Override
    public List<Message> getMessages(Employe emp, LocalDate date1, LocalDate date2) {
        return  messageRepository.findMessagesByDateenvoiIsBetweenAndEmetteur(date1,date2,emp);
    }

}
