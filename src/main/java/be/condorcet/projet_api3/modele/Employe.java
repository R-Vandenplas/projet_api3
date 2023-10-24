package be.condorcet.projet_api3.modele;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
@Entity
@Table(name = "APIEMPLOYE", schema = "ORA21", catalog = "orcl.condorcet.be")
public class Employe {
    @Id
    private int id_employe;
    @NonNull
    private String mail;

    private String nom;


    private String prenom;




    //private List<Message>msg =new ArrayList<>();



    }







