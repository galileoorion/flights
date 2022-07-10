package com.travix.medusa.busyflights.service.toughjet;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ToughJetResponseMapperTest {

    private ToughJetResponseMapper responseMapper;

    @BeforeEach
    void setUp() {
        responseMapper = new ToughJetResponseMapper();
    }

    @Test
    void map() {
        ToughJetResponse toughJetResponse = new ToughJetResponse();
        toughJetResponse.setBasePrice(500);
        toughJetResponse.setDiscount(11);
        toughJetResponse.setTax(0.08);
        toughJetResponse.setCarrier("ToughJet");
        toughJetResponse.setDepartureAirportName("LHR");
        toughJetResponse.setArrivalAirportName("SFO");
        String departure = "2022-12-03T10:15:30Z";
        toughJetResponse.setOutboundDateTime(departure);
        String arrival = "2023-01-03T10:15:30Z";
        toughJetResponse.setInboundDateTime(arrival);

        // when
        BusyFlightsResponse busyFlightsResponse = responseMapper.map(toughJetResponse);

        // then
        assertThat(busyFlightsResponse)
                .hasFieldOrPropertyWithValue("airline", toughJetResponse.getCarrier())
                .hasFieldOrPropertyWithValue("supplier", "ToughJet")
                .hasFieldOrPropertyWithValue("departureAirportCode", toughJetResponse.getDepartureAirportName())
                .hasFieldOrPropertyWithValue("destinationAirportCode", toughJetResponse.getArrivalAirportName())
                .hasFieldOrPropertyWithValue("departureDate", "2022-12-03T10:15:30")
                .hasFieldOrPropertyWithValue("arrivalDate", "2023-01-03T10:15:30")
                .hasFieldOrPropertyWithValue("fare", 528.12);
    }
}