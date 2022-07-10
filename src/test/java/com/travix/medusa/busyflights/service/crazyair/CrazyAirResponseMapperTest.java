package com.travix.medusa.busyflights.service.crazyair;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CrazyAirResponseMapperTest {

    private CrazyAirResponseMapper responseMapper;

    @BeforeEach
    void setUp() {
        responseMapper = new CrazyAirResponseMapper();
    }

    @Test
    void map() {
        // given
        String departure = "2022-12-03T10:15:30";
        String arrival = "2023-01-03T10:15:30";

        CrazyAirResponse crazyAirResponse = new CrazyAirResponse();
        crazyAirResponse.setDepartureDate(departure);
        crazyAirResponse.setArrivalDate(arrival);
        crazyAirResponse.setCabinclass("Economy");
        crazyAirResponse.setDepartureAirportCode("LHR");
        crazyAirResponse.setDestinationAirportCode("SFO");
        crazyAirResponse.setPrice(500.2525);


        // when
        BusyFlightsResponse busyFlightsResponse = responseMapper.map(crazyAirResponse);

        // then
        assertThat(busyFlightsResponse)
                .hasFieldOrPropertyWithValue("airline", crazyAirResponse.getAirline())
                .hasFieldOrPropertyWithValue("supplier", "CrazyAir")
                .hasFieldOrPropertyWithValue("departureAirportCode", crazyAirResponse.getDepartureAirportCode())
                .hasFieldOrPropertyWithValue("destinationAirportCode", crazyAirResponse.getDestinationAirportCode())
                .hasFieldOrPropertyWithValue("departureDate", departure)
                .hasFieldOrPropertyWithValue("arrivalDate", arrival)
                .hasFieldOrPropertyWithValue("fare", 500.25);
    }
}