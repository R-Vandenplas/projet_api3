package be.condorcet.projet_api3.modele;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "API_SERVICE", schema = "ORA21", catalog = "orcl.condorcet.be")
public class Service {

    @Id
    private int id_service;

    private String nom;


    private BigDecimal budget;

    //private List<Employe>employes=new ArrayList<>();

}
