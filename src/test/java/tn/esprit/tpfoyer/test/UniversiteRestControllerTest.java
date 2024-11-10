package tn.esprit.tpfoyer.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tn.esprit.tpfoyer.control.UniversiteRestController;
import tn.esprit.tpfoyer.entity.Universite;
import tn.esprit.tpfoyer.service.IUniversiteService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UniversiteRestControllerTest {

    @Mock
    private IUniversiteService universiteService;

    @InjectMocks
    private UniversiteRestController universiteRestController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUniversites() {
        List<Universite> mockUniversites = new ArrayList<>();
        when(universiteService.retrieveAllUniversites()).thenReturn(mockUniversites);

        List<Universite> result = universiteRestController.getUniversites();

        assertEquals(mockUniversites, result);
        verify(universiteService, times(1)).retrieveAllUniversites();
    }

    @Test
    void testRetrieveUniversite() {
        Long universiteId = 1L;
        Universite mockUniversite = new Universite();
        when(universiteService.retrieveUniversite(universiteId)).thenReturn(mockUniversite);

        Universite result = universiteRestController.retrieveUniversite(universiteId);

        assertEquals(mockUniversite, result);
        verify(universiteService, times(1)).retrieveUniversite(universiteId);
    }

    @Test
    void testAddUniversite() {
        Universite universite = new Universite();
        when(universiteService.addUniversite(universite)).thenReturn(universite);

        Universite result = universiteRestController.addUniversite(universite);

        assertEquals(universite, result);
        verify(universiteService, times(1)).addUniversite(universite);
    }

    @Test
    void testModifyUniversite() {
        Universite universite = new Universite();
        when(universiteService.modifyUniversite(universite)).thenReturn(universite);

        Universite result = universiteRestController.modifyUniversite(universite);

        assertEquals(universite, result);
        verify(universiteService, times(1)).modifyUniversite(universite);
    }

    @Test
    void testRemoveUniversite() {
        Long universiteId = 1L;

        universiteRestController.removeUniversite(universiteId);

        verify(universiteService, times(1)).removeUniversite(universiteId);
    }
}
