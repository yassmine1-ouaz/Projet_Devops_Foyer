package tn.esprit.tpfoyer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.tpfoyer.entity.Bloc;
import tn.esprit.tpfoyer.service.IBlocService;

import java.util.List;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class BlocServicesImplTest {

    @Autowired
    IBlocService blocServices;

    @Test
    @Order(1)
    public void testRetrieveBlocs() {
        List<Bloc> listBlocs = blocServices.retrieveAllBlocs();
        Assertions.assertEquals(2, listBlocs.size());  // Remplacez 0 par le nombre attendu si vous avez des données
    }

    @Test
    @Order(2)
    public void testAddBloc() {
        Bloc bloc = new Bloc();
        bloc.setNomBloc("Bloc B");
        bloc.setCapaciteBloc(50L);

        Bloc savedBloc = blocServices.addBloc(bloc);
        Assertions.assertNotNull(savedBloc);
        Assertions.assertEquals("Bloc B", savedBloc.getNomBloc());
    }

    @Test
    @Order(3)
    public void testDeleteBloc() {
        blocServices.removeBloc(1L); // Remplacez 1L par l'ID de test d'un bloc existant si nécessaire
        List<Bloc> listBlocs = blocServices.retrieveAllBlocs();
        Assertions.assertTrue(listBlocs.stream().noneMatch(bloc -> bloc.getIdBloc() == 1L));
    }
}
