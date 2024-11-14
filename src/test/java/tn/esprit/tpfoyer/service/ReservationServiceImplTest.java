package tn.esprit.tpfoyer.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tpfoyer.entity.Reservation;
import tn.esprit.tpfoyer.repository.ReservationRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReservationServiceImplTest {

    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private ReservationServiceImpl reservationService;

    private Reservation reservation;

    @BeforeEach
    void setUp() {
        reservation = new Reservation();
        reservation.setIdReservation("res123");
        reservation.setAnneeUniversitaire(new Date());
        reservation.setEstValide(true);
    }

    @Test
    void testRetrieveAllReservations() {
        when(reservationRepository.findAll()).thenReturn(List.of(reservation));

        List<Reservation> result = reservationService.retrieveAllReservations();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(reservation.getIdReservation(), result.get(0).getIdReservation());
        verify(reservationRepository, times(1)).findAll();
    }

    @Test
    void testRetrieveReservation() {
        when(reservationRepository.findById("res123")).thenReturn(Optional.of(reservation));

        Reservation result = reservationService.retrieveReservation("res123");

        assertNotNull(result);
        assertEquals(reservation.getIdReservation(), result.getIdReservation());
        verify(reservationRepository, times(1)).findById("res123");
    }

    @Test
    void testAddReservation() {
        when(reservationRepository.save(reservation)).thenReturn(reservation);

        Reservation result = reservationService.addReservation(reservation);

        assertNotNull(result);
        assertEquals(reservation.getIdReservation(), result.getIdReservation());
        verify(reservationRepository, times(1)).save(reservation);
    }

    @Test
    void testModifyReservation() {
        when(reservationRepository.save(reservation)).thenReturn(reservation);

        Reservation result = reservationService.modifyReservation(reservation);

        assertNotNull(result);
        assertEquals(reservation.getIdReservation(), result.getIdReservation());
        verify(reservationRepository, times(1)).save(reservation);
    }

    @Test
    void testRemoveReservation() {
        String reservationId = "res123";

        doNothing().when(reservationRepository).deleteById(reservationId);

        reservationService.removeReservation(reservationId);

        verify(reservationRepository, times(1)).deleteById(reservationId);
    }

    @Test
    void testFindReservationsByDateAndStatus() {
        Date date = new Date();
        boolean status = true;
        when(reservationRepository.findAllByAnneeUniversitaireBeforeAndEstValide(date, status))
                .thenReturn(List.of(reservation));

        List<Reservation> result = reservationService.trouverResSelonDateEtStatus(date, status);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(reservation.getIdReservation(), result.get(0).getIdReservation());
        verify(reservationRepository, times(1))
                .findAllByAnneeUniversitaireBeforeAndEstValide(date, status);
    }
}
