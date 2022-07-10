package com.travix.medusa.busyflights.service.crazyair;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CrazyAirRequestMapperTest {

    private CrazyAirRequestMapper requestMapper;

    @BeforeEach
    void setUp() {
        requestMapper = new CrazyAirRequestMapper();
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
        CrazyAirRequest crazyAirRequest = requestMapper.map(busyFlightsRequest);

        // then
        assertThat(crazyAirRequest)
                .hasFieldOrPropertyWithValue("origin", busyFlightsRequest.getOrigin())
                .hasFieldOrPropertyWithValue("destination", busyFlightsRequest.getDestination())
                .hasFieldOrPropertyWithValue("passengerCount", busyFlightsRequest.getNumberOfPassengers())
                .hasFieldOrPropertyWithValue("departureDate", busyFlightsRequest.getDepartureDate())
                .hasFieldOrPropertyWithValue("returnDate", busyFlightsRequest.getReturnDate());
    }
}