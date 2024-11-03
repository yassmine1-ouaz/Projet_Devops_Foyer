package tn.esprit.tpfoyer.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import tn.esprit.tpfoyer.entity.Foyer;

import jakarta.persistence.EntityNotFoundException;
import tn.esprit.tpfoyer.repository.FoyerRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FoyerServiceMockitoTest {

    @Mock
    private FoyerRepository foyerRepository;

    @InjectMocks
    private FoyerServiceImpl foyerService;

    private Foyer foyer1;
    private Foyer foyer2;

    @BeforeEach
    void setUp() {
        foyer1 = new Foyer(1L, "Foyer A", 100, null, null);
        foyer2 = new Foyer(2L, "Foyer B", 150, null, null);
    }

    @Test
    void retrieveAllFoyers_ShouldReturnListOfFoyers() {
        // Arrange
        List<Foyer> foyerList = Arrays.asList(foyer1, foyer2);
        when(foyerRepository.findAll()).thenReturn(foyerList);

        // Act
        List<Foyer> result = foyerService.retrieveAllFoyers();
        // Assert
        assertEquals(2, result.size());
        verify(foyerRepository, times(1)).findAll();
    }

    @Test
    void retrieveFoyer_ExistingId_ShouldReturnFoyer() {
        // Arrange
        Long foyerId = 1L;
        when(foyerRepository.findById(foyerId)).thenReturn(Optional.of(foyer1));

        // Act
        Foyer result = foyerService.retrieveFoyer(foyerId);

        // Assert
        assertNotNull(result);
        assertEquals("Foyer A", result.getNomFoyer());
        verify(foyerRepository, times(1)).findById(foyerId);
    }

    @Test
    void retrieveFoyer_NonExistingId_ShouldThrowException() {
        // Arrange
        Long nonExistingId = 99L;
        when(foyerRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(EntityNotFoundException.class, () -> foyerService.retrieveFoyer(nonExistingId));
        verify(foyerRepository, times(1)).findById(nonExistingId);
    }

    @Test
    void addFoyer_ShouldSaveFoyer() {
        // Arrange
        Foyer newFoyer = new Foyer(null, "Foyer C", 200, null, null);
        when(foyerRepository.save(newFoyer)).thenReturn(foyer1);

        // Act
        Foyer savedFoyer = foyerService.addFoyer(newFoyer);

        // Assert
        assertNotNull(savedFoyer);
        verify(foyerRepository, times(1)).save(newFoyer);
    }

    @Test
    void modifyFoyer_ShouldUpdateFoyer() {
        // Arrange
        Foyer updatedFoyer = new Foyer(1L, "Updated Foyer", 120, null, null);
        when(foyerRepository.save(updatedFoyer)).thenReturn(updatedFoyer);

        // Act
        Foyer result = foyerService.modifyFoyer(updatedFoyer);

        // Assert
        assertNotNull(result);
        assertEquals("Updated Foyer", result.getNomFoyer());
        verify(foyerRepository, times(1)).save(updatedFoyer);
    }

    @Test
    void removeFoyer_ShouldDeleteFoyer() {
        // Arrange
        Long foyerId = 1L;
        doNothing().when(foyerRepository).deleteById(foyerId);

        // Act
        foyerService.removeFoyer(foyerId);

        // Assert
        verify(foyerRepository, times(1)).deleteById(foyerId);
    }
}
