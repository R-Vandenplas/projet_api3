package be.condorcet.projet_api3;

import be.condorcet.projet_api3.modele.Service;
import be.condorcet.projet_api3.repositories.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/services")
public class GestService {
    @Autowired     //instanciation "automatique" par le framework avec les paramètres indiqués, il s'agit d'un singleton
    ServiceRepository serviceRepository;
    @Autowired
    CacheManager cacheManager;

    public void evictAllCaches(){
        cacheManager.getCacheNames().stream().forEach(cacheName -> cacheManager.getCache(cacheName).clear());
    }
    @RequestMapping("/tous")
    public String affTous(Map<String, Object> model) {
        evictAllCaches();
        System.out.println("recherche services");
        List<Service> liste;
        try {
            liste = serviceRepository.findAll();
            model.put("mesServices", liste);
        } catch (Exception e) {
            System.out.println("----------erreur lors de la recherche-------- " + e);
            return "error";
        }
        return "affichagetousServices";
    }

    @RequestMapping("/selection")
    String selection(@RequestParam("numserv") int numserv, Map<String, Object> model) {
        Service sv = null;
        Optional<Service> osv;
        try {
            osv = serviceRepository.findById(numserv);
            if(osv.isPresent()) sv=osv.get();
            else throw new Exception("service inconnu");
            model.put("monService", sv);

        } catch (Exception e) {
            System.out.println("erreur lors de la lecture " + e);
            model.put("error",e);
            return "error";
        }
        return "affichageService";
    }
}
