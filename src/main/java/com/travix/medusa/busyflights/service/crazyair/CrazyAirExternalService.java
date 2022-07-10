package com.travix.medusa.busyflights.service.crazyair;

import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.service.ExternalService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CrazyAirExternalService implements ExternalService<CrazyAirRequest, CrazyAirResponse> {

    @Override
    public List<CrazyAirResponse> search(CrazyAirRequest request) {
        CrazyAirResponse response1 = new CrazyAirResponse();
        response1.setAirline("CrazyAir");
        response1.setArrivalDate(LocalDate.of(2023, 1,1).toString());
        response1.setCabinclass("Economy");
        response1.setPrice(150d);
        response1.setDestinationAirportCode("LHR");

        CrazyAirResponse response2 = new CrazyAirResponse();
        response2.setAirline("CrazyAir");
        response2.setArrivalDate(LocalDate.of(2023, 1,1).toString());
        response2.setCabinclass("Economy");
        response2.setPrice(150d);
        response2.setDestinationAirportCode("LHR");
        return List.of(response1, response2);
    }
}
