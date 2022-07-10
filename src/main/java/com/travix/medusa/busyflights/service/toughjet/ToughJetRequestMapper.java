package com.travix.medusa.busyflights.service.toughjet;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.service.RequestMapper;
import org.springframework.stereotype.Service;

@Service
public class ToughJetRequestMapper implements RequestMapper<ToughJetRequest> {
    @Override
    public ToughJetRequest map(BusyFlightsRequest request) {
        ToughJetRequest toughJetRequest = new ToughJetRequest();

        toughJetRequest.setFrom(request.getOrigin());
        toughJetRequest.setTo(request.getDestination());
        toughJetRequest.setOutboundDate(request.getDepartureDate());
        toughJetRequest.setInboundDate(request.getReturnDate());
        toughJetRequest.setNumberOfAdults(request.getNumberOfPassengers()); // children can travel with adult tickets

        return toughJetRequest;
    }
}
