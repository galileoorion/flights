package com.travix.medusa.busyflights.service.toughjet;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ToughJetRequestMapperTest {

    private ToughJetRequestMapper requestMapper;

    @BeforeEach
    void setUp() {
        requestMapper = new ToughJetRequestMapper();
    }

    @Test
    void map() {
        BusyFlightsRequest busyFlightsRequest = new BusyFlightsRequest();
        busyFlightsRequest.setOrigin("LHR");
        busyFlightsRequest.setDestination("SFO");
        busyFlightsRequest.setDepartureDate("2022-12-03");
        busyFlightsRequest.setReturnDate("2023-01-03");
        busyFlightsRequest.setNumberOfPassengers(2);

        // when
        ToughJetRequest toughJetRequest = requestMapper.map(busyFlightsRequest);

        // then
        assertThat(toughJetRequest)
                .hasFieldOrPropertyWithValue("from", busyFlightsRequest.getOrigin())
                .hasFieldOrPropertyWithValue("to", busyFlightsRequest.getDestination())
                .hasFieldOrPropertyWithValue("numberOfAdults", busyFlightsRequest.getNumberOfPassengers())
                .hasFieldOrPropertyWithValue("outboundDate", busyFlightsRequest.getDepartureDate())
                .hasFieldOrPropertyWithValue("inboundDate", busyFlightsRequest.getReturnDate());
    }
}