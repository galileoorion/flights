package com.travix.medusa.busyflights.service.toughjet;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import com.travix.medusa.busyflights.service.ResponseMapper;
import com.travix.medusa.busyflights.utils.DateTimeUtils;
import com.travix.medusa.busyflights.utils.MathUtils;
import org.springframework.stereotype.Service;


@Service
public class ToughJetResponseMapper implements ResponseMapper<ToughJetResponse> {

    private static final String supplier = "ToughJet";

    @Override
    public BusyFlightsResponse map(ToughJetResponse toughJetResponse) {
        BusyFlightsResponse response = new BusyFlightsResponse();

        response.setAirline(toughJetResponse.getCarrier());
        response.setSupplier(supplier);
        response.setDepartureAirportCode(toughJetResponse.getDepartureAirportName());
        response.setDestinationAirportCode(toughJetResponse.getArrivalAirportName());

        String departureDate = DateTimeUtils.convertResponseDateTime(toughJetResponse.getOutboundDateTime(), ToughJetResponse.DATE_TIME_FORMAT);
        response.setDepartureDate(departureDate);
        String arrivalDate = DateTimeUtils.convertResponseDateTime(toughJetResponse.getInboundDateTime(), ToughJetResponse.DATE_TIME_FORMAT);
        response.setArrivalDate(arrivalDate);

        response.setFare(calculateFare(toughJetResponse));

        return response;
    }

    private double calculateFare(ToughJetResponse toughJetResponse){
        return MathUtils.roundTo2Decimals((toughJetResponse.getBasePrice() - toughJetResponse.getDiscount()) * (1 + toughJetResponse.getTax()));
    }
}
