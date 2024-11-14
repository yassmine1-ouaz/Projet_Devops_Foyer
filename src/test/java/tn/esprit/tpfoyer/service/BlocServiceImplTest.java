package tn.esprit.tpfoyer.service;

import static org.junit.jupiter.api.Assertions.*;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tpfoyer.entity.Bloc;
import tn.esprit.tpfoyer.repository.BlocRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
@RequiredArgsConstructor
public class BlocServiceImplTest {

    @Mock
    BlocRepository blocRepository;

    @InjectMocks
    BlocServiceImpl blocService;

    Bloc bloc = Bloc.builder().nomBloc("bloc esprit").capaciteBloc(200).build();
    List<Bloc> listBloc = new ArrayList<Bloc>() {
        {
            add(new Bloc(1L, "bloc A", 150, null, null));
            add(new Bloc(2L, "bloc B", 100, null, null));
        }
    };

    @Test
    void testRetrieveBloc() {
        Mockito.when(blocRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(bloc));
        Bloc retrievedBloc = blocService.retrieveBloc(1L);
        Assertions.assertNotNull(retrievedBloc);
    }

    @Test
    void testAddBloc() {
        Bloc blocSaved = Bloc.builder().idBloc(1L).nomBloc("bloc esprit").capaciteBloc(200).build();

        Mockito.when(blocRepository.save(Mockito.any(Bloc.class))).thenReturn(blocSaved);

        Bloc bloc1 = blocService.addBloc(bloc);

        Assertions.assertNotNull(bloc1);
        Assertions.assertEquals(1L, bloc1.getIdBloc());

        Mockito.verify(blocRepository).save(bloc);
    }

    @Test
    void testRetrieveAllBlocs() {
        Mockito.when(blocRepository.findAll()).thenReturn(listBloc);
        Assertions.assertEquals(2, blocService.retrieveAllBlocs().size());
    }

    @Test
    void testModifyBloc() {
        Bloc modifiedBloc = Bloc.builder().idBloc(1L).nomBloc("bloc modified").capaciteBloc(180).build();

        Mockito.when(blocRepository.save(Mockito.any(Bloc.class))).thenAnswer(invocation -> {
            Bloc blocArg = invocation.getArgument(0);
            listBloc.get(0).setNomBloc(blocArg.getNomBloc());
            listBloc.get(0).setCapaciteBloc(blocArg.getCapaciteBloc());
            return listBloc.get(0);
        });

        blocService.modifyBloc(modifiedBloc);
        Assertions.assertEquals("bloc modified", listBloc.get(0).getNomBloc());
    }

    @Test
    void testRemoveBloc() {
        Long blocId = 1L;

        blocService.removeBloc(blocId);

        Mockito.verify(blocRepository).deleteById(blocId);
    }
}