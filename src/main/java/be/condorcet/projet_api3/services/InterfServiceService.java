package be.condorcet.projet_api3.services;

import be.condorcet.projet_api3.modele.Service;

import java.math.BigDecimal;
import java.util.List;

public interface InterfServiceService extends InterfService<Service> {

    public List<Service> read(String nom);

    Service read(String nom, BigDecimal budget);

    public List<Service> read(BigDecimal budget);
}

