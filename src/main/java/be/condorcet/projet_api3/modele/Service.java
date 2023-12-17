package be.condorcet.projet_api3.modele;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "APISERVICE", schema = "ORA21", catalog = "orcl.condorcet.be")
public class Service {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "service_generator")
    @SequenceGenerator(name="service_generator", sequenceName = "APISERVICE_SEQ", allocationSize=1)
    private Integer idservice;

    private String nom;


    private BigDecimal budget;
    @JsonIgnore
    @OneToMany(mappedBy = "service")
    @ToString.Exclude
    private List<Employe> employes=new ArrayList<>();

}