package be.condorcet.projet_api3.modele;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employe_generator")
    @SequenceGenerator(name="employe_generator", sequenceName = "APIEMPLOYE_SEQ", allocationSize=1)
    private Integer idemploye;
    @NonNull
    private String mail;
    @NonNull
    private String nom;
    @NonNull
    private String prenom;
    @NonNull @ManyToOne() @JoinColumn(name="IDSERVICE")
    private Service service;
    @JsonIgnore
    @OneToMany(mappedBy = "emetteur")
    @ToString.Exclude
    private List<Message>msg =new ArrayList<>();

}







