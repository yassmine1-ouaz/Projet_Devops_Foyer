package tn.esprit.tpfoyer.service;


import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.entity.Bloc;
import tn.esprit.tpfoyer.repository.BlocRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j  // Simple Loggining Façade For Java
public class BlocServiceImpl  implements IBlocService {

    private static final Logger logger = LogManager.getLogger(BlocServiceImpl.class);

    BlocRepository blocRepository;

    @Scheduled(fixedRate = 30000) // millisecondes // cron fixedRate
    //@Scheduled(cron="0/15 * * * * *")


    public List<Bloc> retrieveAllBlocs() {
        List<Bloc> listB = blocRepository.findAll();
        logger.info("Total blocks: " + listB.size());
        for (Bloc b : listB) {
            logger.debug("Block: " + b);
        }
        return listB;
    }


    // Exemple sans Keywords :

    @Transactional
    public List<Bloc> retrieveBlocsSelonCapacite(long c) {
        logger.info("Récupération des blocs avec une capacité supérieure ou égale à {}", c);
        List<Bloc> listB = blocRepository.findAll();
        List<Bloc> listBselonC = new ArrayList<>();

        for (Bloc b : listB) {
            if (b.getCapaciteBloc() >= c) {
                logger.debug("Ajout du bloc avec ID: {} et capacité: {}", b.getIdBloc(), b.getCapaciteBloc());
                listBselonC.add(b);
            }
        }

        logger.info("Nombre total de blocs trouvés: {}", listBselonC.size());
        return listBselonC;
    }

    @Transactional
    public Bloc retrieveBloc(Long blocId) {
        logger.info("Récupération du bloc avec ID: {}", blocId);
        Bloc bloc = blocRepository.findById(blocId).orElse(null);
        if (bloc != null) {
            logger.info("Bloc trouvé : {}", bloc);
        } else {
            logger.warn("Aucun bloc trouvé avec l'ID: {}", blocId);
        }
        return bloc;
    }


    public Bloc addBloc(Bloc c) {
        logger.info("Ajout d'un nouveau bloc : {}", c);
        Bloc savedBloc = blocRepository.save(c);
        logger.info("Bloc ajouté avec succès avec ID : {}", savedBloc.getIdBloc());
        return savedBloc;
    }

    public Bloc modifyBloc(Bloc bloc) {
        logger.info("Modification du bloc avec ID: {}", bloc.getIdBloc());
        Bloc updatedBloc = blocRepository.save(bloc);
        logger.info("Bloc modifié avec succès : {}", updatedBloc);
        return updatedBloc;
    }
    public void removeBloc(Long blocId) {
        logger.info("Suppression du bloc avec ID: {}", blocId);
        blocRepository.deleteById(blocId);
        logger.info("Bloc avec ID: {} supprimé", blocId);
    }



    public List<Bloc> trouverBlocsSansFoyer() {
        logger.info("Récupération des blocs sans foyer associé");
        List<Bloc> blocsSansFoyer = blocRepository.findAllByFoyerIsNull();
        logger.info("Nombre de blocs sans foyer : {}", blocsSansFoyer.size());
        return blocsSansFoyer;
    }
    public List<Bloc> trouverBlocsParNomEtCap(String nb, long c) {
        logger.info("Récupération des blocs avec le nom '{}' et la capacité {}", nb, c);
        List<Bloc> blocs = blocRepository.findAllByNomBlocAndCapaciteBloc(nb, c);
        logger.info("Nombre de blocs trouvés : {}", blocs.size());
        return blocs;
    }

}
