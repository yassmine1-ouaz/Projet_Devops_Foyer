package tn.esprit.tpfoyer.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.tpfoyer.control.ReservationRestController;
import tn.esprit.tpfoyer.entity.Reservation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ReservationRestControllerTest {

    @Mock
    private IReservationService reservationService;

    @InjectMocks
    private ReservationRestController reservationRestController;

    private Reservation reservation;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        reservation = new Reservation();
        reservation.setIdReservation("res123");
        reservation.setAnneeUniversitaire(new Date());
        reservation.setEstValide(true);
    }

    @Test
    void testGetReservations() {
        List<Reservation> mockReservations = new ArrayList<>();
        mockReservations.add(reservation);
        when(reservationService.retrieveAllReservations()).thenReturn(mockReservations);

        List<Reservation> result = reservationRestController.getReservations();

        assertEquals(mockReservations, result);
        verify(reservationService, times(1)).retrieveAllReservations();
    }

    @Test
    void testRetrieveReservation() {
        String reservationId = "res123";
        when(reservationService.retrieveReservation(reservationId)).thenReturn(reservation);

        Reservation result = reservationRestController.retrieveReservation(reservationId);

        assertEquals(reservation, result);
        verify(reservationService, times(1)).retrieveReservation(reservationId);
    }

    @Test
    void testAddReservation() {
        when(reservationService.addReservation(reservation)).thenReturn(reservation);

        Reservation result = reservationRestController.addReservation(reservation);

        assertEquals(reservation, result);
        verify(reservationService, times(1)).addReservation(reservation);
    }

    @Test
    void testModifyReservation() {
        when(reservationService.modifyReservation(reservation)).thenReturn(reservation);

        Reservation result = reservationRestController.modifyReservation(reservation);

        assertEquals(reservation, result);
        verify(reservationService, times(1)).modifyReservation(reservation);
    }

    @Test
    void testRemoveReservation() {
        String reservationId = "res123";

        reservationRestController.removeReservation(reservationId);

        verify(reservationService, times(1)).removeReservation(reservationId);
    }

    @Test
    void testRetrieveReservationParDateEtStatus() {
        Date date = new Date();
        boolean status = true;
        List<Reservation> mockReservations = List.of(reservation);
        when(reservationService.trouverResSelonDateEtStatus(date, status)).thenReturn(mockReservations);

        List<Reservation> result = reservationRestController.retrieveReservationParDateEtStatus(date, status);

        assertEquals(mockReservations, result);
        verify(reservationService, times(1)).trouverResSelonDateEtStatus(date, status);
    }
}