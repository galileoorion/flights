package com.travix.medusa.busyflights.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class FlightsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void search_whenAllPropertiesValid_ReturnsOk() throws Exception {
        // given
        BusyFlightsRequest body = new BusyFlightsRequest();
        body.setOrigin("LHR");
        body.setDestination("SFO");
        body.setDepartureDate("2022-12-03");
        body.setReturnDate("2023-01-03");
        body.setNumberOfPassengers(2);
        String content = objectMapper.writeValueAsString(body);

        // when
        mockMvc.perform(post("/search")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/requests.csv", numLinesToSkip = 1)
    void requestTest(String origin, String destination, String departureDate, String returnDate, int numberOfPassengers) throws Exception {
        BusyFlightsRequest request = new BusyFlightsRequest();
        request.setOrigin(origin);
        request.setDestination(destination);
        request.setDepartureDate(departureDate);
        request.setReturnDate(returnDate);
        request.setNumberOfPassengers(numberOfPassengers);

        String content = objectMapper.writeValueAsString(request);

        // when
        mockMvc.perform(post("/search")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}