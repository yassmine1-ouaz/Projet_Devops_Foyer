package tn.esprit.tpfoyer.service;

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
import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.repository.ChambreRepository;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
@RequiredArgsConstructor
class ChambreServiceImplTest {

    @Mock
    ChambreRepository chambreRepository;
    @InjectMocks
    ChambreServiceImpl chambreService;

    Chambre chambre = Chambre.builder().idChambre(3L).numeroChambre(17L).build();
    List<Chambre> listChambre = new ArrayList<>(){
        {
            add(new Chambre(1L,10));
            add(new Chambre(2L,4));
        }
    };


    @Test
    void testRetrieveChambre() {

        Mockito.when(chambreRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(chambre));
        Chambre chambre1 = chambreService.retrieveChambre(3L);
        Assertions.assertNotNull(chambre1,"testRetrieveChambre should pass.");
    }

    @Test
    void testAddChambre() {

        Chambre chambresaved = Chambre.builder().idChambre(1L).numeroChambre(150).build();
        Mockito.when(chambreRepository.save(Mockito.any(Chambre.class))).thenReturn(chambresaved);

        Chambre chambre1 = chambreService.addChambre(chambre);
        Assertions.assertNotNull(chambre1);
        Assertions.assertEquals(1L, chambre1.getIdChambre(), "id should exist and match");

        Mockito.verify(chambreRepository).save(chambre);
    }

    @Test
    void testretrieveAllChambres() {
        Mockito.when(chambreRepository.findAll()).thenReturn(listChambre);
        Assertions.assertEquals(2,chambreService.retrieveAllChambres().size());
        System.out.println("List : " + listChambre);
    }




    @Test
    void modifyChambre() {
        Chambre trychambre = Chambre.builder().idChambre(1L).numeroChambre(10).build();
        Mockito.when(chambreRepository.save(Mockito.any(Chambre.class))).thenAnswer(inv -> {
            inv.getArgument(0);
            listChambre.get(0).setNumeroChambre(trychambre.getNumeroChambre());


            return listChambre.get(0) ;
        });
        chambreService.modifyChambre(trychambre);
        Assertions.assertEquals(10,listChambre.get(0).getNumeroChambre());

    }

    @Test
    void removeChambre() {

        Long chambreid = 1L;

        chambreService.removeChambre(chambreid);

        Mockito.verify(chambreRepository).deleteById(chambreid);
    }
}