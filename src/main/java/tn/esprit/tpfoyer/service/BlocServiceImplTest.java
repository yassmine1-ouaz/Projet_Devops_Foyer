package tn.esprit.tpfoyer.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.tpfoyer.entity.Bloc;
import tn.esprit.tpfoyer.repository.BlocRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

    public class BlocServiceImplTest {

        @Mock
        private BlocRepository blocRepository;

        @InjectMocks
        private BlocServiceImpl blocService;

        @BeforeEach
        public void setUp() {
            MockitoAnnotations.openMocks(this);
        }

        @Test
        public void testRetrieveBlocsSelonCapacite() {
            // Préparation des données de test
            Bloc bloc1 = new Bloc();
            bloc1.setCapaciteBloc(100);
            Bloc bloc2 = new Bloc();
            bloc2.setCapaciteBloc(50);

            List<Bloc> blocs = new ArrayList<>();
            blocs.add(bloc1);
            blocs.add(bloc2);

            when(blocRepository.findAll()).thenReturn(blocs);

            // Appel de la méthode à tester
            List<Bloc> result = blocService.retrieveBlocsSelonCapacite(60);

            // Vérification des résultats
            assertEquals(1, result.size());
            assertTrue(result.contains(bloc1));
            assertFalse(result.contains(bloc2));
        }

        @Test
        public void testRetrieveBloc() {
            // Préparation des données de test
            Bloc bloc = new Bloc();
            bloc.setIdBloc(1L);
            bloc.setNomBloc("Bloc Test");

            when(blocRepository.findById(1L)).thenReturn(Optional.of(bloc));

            // Appel de la méthode à tester
            Bloc result = blocService.retrieveBloc(1L);

            // Vérification des résultats
            assertNotNull(result);
            assertEquals("Bloc Test", result.getNomBloc());
            assertEquals(1L, result.getIdBloc());
        }

        @Test
        public void testAddBloc() {
            // Préparation des données de test
            Bloc bloc = new Bloc();
            bloc.setNomBloc("Nouveau Bloc");

            when(blocRepository.save(bloc)).thenReturn(bloc);

            // Appel de la méthode à tester
            Bloc result = blocService.addBloc(bloc);

            // Vérification des résultats
            assertNotNull(result);
            assertEquals("Nouveau Bloc", result.getNomBloc());
        }

        @Test
        public void testRemoveBloc() {
            // Appel de la méthode sans exceptions
            assertDoesNotThrow(() -> blocService.removeBloc(1L));

            // Vérification que la méthode `deleteById` a bien été appelée
            verify(blocRepository, times(1)).deleteById(1L);
        }
    }


