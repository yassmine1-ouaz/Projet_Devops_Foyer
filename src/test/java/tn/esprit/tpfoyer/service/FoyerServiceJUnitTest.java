package tn.esprit.tpfoyer.service;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.tpfoyer.entity.Foyer;
import tn.esprit.tpfoyer.repository.FoyerRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FoyerServiceJUnitTest {

    @Autowired
    private FoyerServiceImpl foyerService;

    @Autowired
    private FoyerRepository foyerRepository;
    private Foyer foyer;

    @Test
    @Order(1)
    void addFoyer() {
        foyer = new Foyer();
        foyer.setNomFoyer("Foyer A");
        foyer.setCapaciteFoyer(100);

        Foyer savedFoyer = foyerService.addFoyer(foyer);

        assertNotNull(savedFoyer);
        assertEquals("Foyer A", savedFoyer.getNomFoyer());
        foyer = savedFoyer;  // Mettre à jour l'objet foyer ici
        System.out.println("Add Foyer: Ok");
    }

    @Test
    @Order(2)
    void retrieveFoyer() {
        // Assuming contratService.retrieveContrat method returns a Contrat
        Foyer foyer = foyerService.retrieveFoyer(1L); // Replace 1 with the actual ID you want to retrieve

        // Add assertions based on your requirements
        assertNotNull(foyer);
        // Additional assertions...

        System.out.println("RetrievefoyerTest : Ok");
    }

    @Test
    @Order(3)
    void retrieveAllFoyers() {
        // Act
        List<Foyer> foyers = foyerService.retrieveAllFoyers();

        // Assert
        assertNotNull(foyers);
        assertFalse(foyers.isEmpty());
        System.out.println("Retrieve All Foyers: Ok");
    }


    @Test
    @Order(4)
    void removeFoyer() {
        // Act
        foyerService.removeFoyer(1L); // Replace with the actual ID of the foyer to delete

        // Assert
        assertThrows(EntityNotFoundException.class, () -> foyerService.retrieveFoyer(1L));
        System.out.println("Remove Foyer: Ok");
    }
}
