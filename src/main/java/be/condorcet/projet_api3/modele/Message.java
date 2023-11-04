package be.condorcet.projet_api3.modele;

import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
@Entity
@Table(name = "APIMESSAGE", schema = "ORA21", catalog = "orcl.condorcet.be")
public class Message {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "message_generator")
    @SequenceGenerator(name="message_generator", sequenceName = "APIMESSAGE_SEQ", allocationSize=1)
    private int id_message;

    private String objet;
    @NonNull
    private String contenu;
    @NonNull
    private LocalDate dateenvoi;
    @NonNull @ManyToOne @JoinColumn(name="IDEMPLOYEEMETTEUR")
    private Employe emetteur;


}
