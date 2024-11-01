package tn.esprit.tpfoyer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.tpfoyer.entity.Bloc;
import tn.esprit.tpfoyer.entity.Foyer;
import tn.esprit.tpfoyer.repository.BlocRepository;
import tn.esprit.tpfoyer.service.BlocServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class BlocServicesImplMock {

    @Mock
    BlocRepository blocRepository;

    @InjectMocks
    BlocServiceImpl blocServices;

    Bloc bloc1;
    Bloc bloc2;

    @BeforeEach
    public void setup() {
        // Initialisation des objets Bloc avec des données de test
        bloc1 = new Bloc(1L, "Bloc A", 100L, new Foyer(), new HashSet<>());
        bloc2 = new Bloc(2L, "Bloc B", 50L, new Foyer(), new HashSet<>());

        // Mock des méthodes du repository
        when(blocRepository.findAll()).thenReturn(Arrays.asList(bloc1, bloc2));
        when(blocRepository.findById(1L)).thenReturn(Optional.of(bloc1));
        when(blocRepository.save(Mockito.any(Bloc.class))).thenAnswer(invocation -> invocation.getArgument(0));
    }

    @Test
    public void testRetrieveAllBlocs() {
        List<Bloc> blocs = blocServices.retrieveAllBlocs();
        assertEquals(2, blocs.size());  // Vérifie que la méthode retourne les blocs mockés
        assertEquals("Bloc A", blocs.get(0).getNomBloc());
        assertEquals("Bloc B", blocs.get(1).getNomBloc());
    }

    @Test
    public void testAddBloc() {
        Bloc newBloc = new Bloc();
        newBloc.setNomBloc("Bloc C");
        newBloc.setCapaciteBloc(75L);

        Bloc savedBloc = blocServices.addBloc(newBloc);
        assertNotNull(savedBloc);
        assertEquals("Bloc C", savedBloc.getNomBloc());
        assertEquals(75L, savedBloc.getCapaciteBloc());
    }

    @Test
    public void testRetrieveBlocById() {
        Bloc foundBloc = blocServices.retrieveBloc(1L);
        assertNotNull(foundBloc);
        assertEquals("Bloc A", foundBloc.getNomBloc());
        assertEquals(100L, foundBloc.getCapaciteBloc());
    }
}
