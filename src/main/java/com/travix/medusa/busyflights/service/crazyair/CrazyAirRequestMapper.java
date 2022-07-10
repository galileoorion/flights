package com.travix.medusa.busyflights.service.crazyair;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.service.RequestMapper;
import org.springframework.stereotype.Service;

@Service
public class CrazyAirRequestMapper implements RequestMapper<CrazyAirRequest> {
    @Override
    public CrazyAirRequest map(BusyFlightsRequest request) {
        CrazyAirRequest crazyAirRequest = new CrazyAirRequest();

        crazyAirRequest.setOrigin(request.getOrigin());
        crazyAirRequest.setDestination(request.getDestination());
        crazyAirRequest.setDepartureDate(request.getDepartureDate());
        crazyAirRequest.setReturnDate(request.getReturnDate());
        crazyAirRequest.setPassengerCount(request.getNumberOfPassengers());

        return crazyAirRequest;
    }
}
