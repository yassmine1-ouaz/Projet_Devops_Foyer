import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tpfoyer.entity.Etudiant;
import tn.esprit.tpfoyer.repository.EtudiantRepository;
import tn.esprit.tpfoyer.service.EtudiantServiceImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EtudiantServiceImplTest {

    @Mock
    private EtudiantRepository etudiantRepository;

    @InjectMocks
    private EtudiantServiceImpl etudiantService;

    private Etudiant etudiant;

    @BeforeEach
    void setUp() {
        etudiant = new Etudiant();
        etudiant.setIdEtudiant(1L);
        etudiant.setCinEtudiant(12345678L);
        etudiant.setNomEtudiant("Yassmine");
        etudiant.setPrenomEtudiant("OU");
    }

    @Test
    public void testRetrieveEtudiant() {
        when(etudiantRepository.findById(1L)).thenReturn(Optional.of(etudiant));

        Etudiant result = etudiantService.retrieveEtudiant(1L);

        assertNotNull(result);
        assertEquals(etudiant.getIdEtudiant(), result.getIdEtudiant());
        assertEquals(etudiant.getCinEtudiant(), result.getCinEtudiant());
        assertEquals(etudiant.getNomEtudiant(), result.getNomEtudiant());
        verify(etudiantRepository, times(1)).findById(1L);
    }

    @Test
    public void testAddEtudiant() {
        when(etudiantRepository.save(etudiant)).thenReturn(etudiant);

        Etudiant result = etudiantService.addEtudiant(etudiant);

        assertNotNull(result);
        assertEquals(etudiant.getIdEtudiant(), result.getIdEtudiant());
        verify(etudiantRepository, times(1)).save(etudiant);
    }

    // Additional tests for other methods
}
