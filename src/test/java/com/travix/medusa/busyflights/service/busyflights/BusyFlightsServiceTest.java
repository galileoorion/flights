package com.travix.medusa.busyflights.service.busyflights;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.service.crazyair.CrazyAirService;
import com.travix.medusa.busyflights.service.toughjet.ToughJetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BusyFlightsServiceTest {

    private BusyFlightsService busyFlightsService;
    @Mock
    private CrazyAirService crazyAirService;
    @Mock
    private ToughJetService toughJetService;

    @BeforeEach
    void setUp() {
        busyFlightsService = new BusyFlightsService(crazyAirService, toughJetService);
    }

    @Test
    void search_whenBothServiceReturnsResults_ResultsAreOrderedByFare() {
        // given
        BusyFlightsResponse response200 = new BusyFlightsResponse();
        response200.setSupplier("CrazyAir");
        response200.setArrivalDate(LocalDate.of(2023, 1, 1).toString());
        response200.setDepartureDate(LocalDate.of(2022, 12, 1).toString());
        response200.setFare(200);
        response200.setDestinationAirportCode("LHR");

        BusyFlightsResponse response150 = new BusyFlightsResponse();
        response150.setSupplier("CrazyAir");
        response150.setArrivalDate(LocalDate.of(2023, 1, 1).toString());
        response150.setDepartureDate(LocalDate.of(2022, 12, 1).toString());
        response150.setFare(150);
        response150.setDestinationAirportCode("LHR");

        BusyFlightsResponse response175 = new BusyFlightsResponse();
        response175.setSupplier("ToughJet");
        response175.setArrivalDate(LocalDate.of(2023, 1, 1).toString());
        response175.setDepartureDate(LocalDate.of(2022, 12, 1).toString());
        response175.setFare(175);
        response175.setDestinationAirportCode("LHR");


        when(crazyAirService.search(any(BusyFlightsRequest.class)))
                .thenReturn(List.of(response200, response150));
        when(toughJetService.search(any(BusyFlightsRequest.class)))
                .thenReturn(List.of(response175));
        List<BusyFlightsResponse> reponse = busyFlightsService.search(new BusyFlightsRequest());
        assertThat(reponse).containsExactly(response150, response175, response200);
    }

    @Test
    void search_whenCrazyAirFails_ToughJetReturnsResult() {
        // given
        BusyFlightsResponse response175 = new BusyFlightsResponse();
        response175.setSupplier("ToughJet");
        response175.setArrivalDate(LocalDate.of(2023, 1, 1).toString());
        response175.setDepartureDate(LocalDate.of(2022, 12, 1).toString());
        response175.setFare(175);
        response175.setDestinationAirportCode("LHR");


        when(crazyAirService.search(any(BusyFlightsRequest.class)))
                .thenThrow(new RuntimeException());
        when(toughJetService.search(any(BusyFlightsRequest.class)))
                .thenReturn(List.of(response175));

        // when
        List<BusyFlightsResponse> busyFlightsResponses = busyFlightsService.search(new BusyFlightsRequest());

        // then
        assertThat(busyFlightsResponses).containsExactly(response175);
    }

    @Test
    void search_whenToughJetFails_CrazyAirReturnsResult() {
        // given
        BusyFlightsResponse response200 = new BusyFlightsResponse();
        response200.setSupplier("CrazyAir");
        response200.setArrivalDate(LocalDate.of(2023, 1, 1).toString());
        response200.setDepartureDate(LocalDate.of(2022, 12, 1).toString());
        response200.setFare(200);
        response200.setDestinationAirportCode("LHR");

        BusyFlightsResponse response150 = new BusyFlightsResponse();
        response150.setSupplier("CrazyAir");
        response150.setArrivalDate(LocalDate.of(2023, 1, 1).toString());
        response150.setDepartureDate(LocalDate.of(2022, 12, 1).toString());
        response150.setFare(150);
        response150.setDestinationAirportCode("LHR");


        when(crazyAirService.search(any(BusyFlightsRequest.class)))
                .thenReturn(List.of(response200, response150));
        when(toughJetService.search(any(BusyFlightsRequest.class)))
                .thenThrow(new RuntimeException());

        // when
        List<BusyFlightsResponse> busyFlightsResponses = busyFlightsService.search(new BusyFlightsRequest());

        // then
        assertThat(busyFlightsResponses).containsExactly(response150, response200);
    }

    @Test
    void search_whenBothFails_EmptyListIsReturned() {
        // given
        when(crazyAirService.search(any(BusyFlightsRequest.class)))
                .thenThrow(new RuntimeException());
        when(toughJetService.search(any(BusyFlightsRequest.class)))
                .thenThrow(new RuntimeException());

        // when
        List<BusyFlightsResponse> busyFlightsResponses = busyFlightsService.search(new BusyFlightsRequest());

        // then
        assertThat(busyFlightsResponses).isEmpty();
    }
}