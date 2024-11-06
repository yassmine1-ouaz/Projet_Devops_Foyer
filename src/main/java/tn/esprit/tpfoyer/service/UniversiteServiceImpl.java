package tn.esprit.tpfoyer.service;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.entity.Universite;
import tn.esprit.tpfoyer.repository.UniversiteRepository;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Service
@AllArgsConstructor
public class UniversiteServiceImpl implements IUniversiteService {

    private static final Logger logger = LogManager.getLogger(UniversiteServiceImpl.class);

    UniversiteRepository universiteRepository;

    public List<Universite> retrieveAllUniversites() {
        logger.info("Récupération de toutes les universités");
        List<Universite> universites = universiteRepository.findAll();
        logger.info("Nombre total d'universités : {}", universites.size());
        return universites;
    }

    public Universite retrieveUniversite(Long universiteId) {
        logger.info("Récupération de l'université avec ID : {}", universiteId);
        Universite universite = universiteRepository.findById(universiteId).orElse(null);
        if (universite != null) {
            logger.info("Université trouvée : {}", universite);
        } else {
            logger.warn("Aucune université trouvée avec l'ID : {}", universiteId);
        }
        return universite;
    }

    public Universite addUniversite(Universite u) {
        logger.info("Ajout d'une nouvelle université : {}", u);
        Universite savedUniversite = universiteRepository.save(u);
        logger.info("Université ajoutée avec succès avec ID : {}", savedUniversite.getIdUniversite());
        return savedUniversite;
    }

    public Universite modifyUniversite(Universite universite) {
        logger.info("Modification de l'université avec ID : {}", universite.getIdUniversite());
        Universite updatedUniversite = universiteRepository.save(universite);
        logger.info("Université modifiée avec succès : {}", updatedUniversite);
        return updatedUniversite;
    }

    public void removeUniversite(Long universiteId) {
        logger.info("Suppression de l'université avec ID : {}", universiteId);
        universiteRepository.deleteById(universiteId);
        logger.info("Université avec ID : {} supprimée", universiteId);
    }
}
