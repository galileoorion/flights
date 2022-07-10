package com.travix.medusa.busyflights.service.crazyair;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.service.ResponseMapper;
import com.travix.medusa.busyflights.utils.DateTimeUtils;
import com.travix.medusa.busyflights.utils.MathUtils;
import org.springframework.stereotype.Service;

@Service
public class CrazyAirResponseMapper implements ResponseMapper<CrazyAirResponse> {

    private static final String supplier = "CrazyAir";

    @Override
    public BusyFlightsResponse map(CrazyAirResponse crazyAirResponse) {
        BusyFlightsResponse response = new BusyFlightsResponse();

        response.setAirline(crazyAirResponse.getAirline());
        response.setSupplier(supplier);
        response.setDepartureAirportCode(crazyAirResponse.getDepartureAirportCode());
        response.setDestinationAirportCode(crazyAirResponse.getDestinationAirportCode());
        response.setFare(MathUtils.roundTo2Decimals(crazyAirResponse.getPrice()));

        String departureDate = DateTimeUtils.convertResponseDateTime(crazyAirResponse.getDepartureDate(), CrazyAirResponse.DATE_TIME_FORMAT);
        response.setDepartureDate(departureDate);
        String arrivalDate = DateTimeUtils.convertResponseDateTime(crazyAirResponse.getArrivalDate(), CrazyAirResponse.DATE_TIME_FORMAT);
        response.setArrivalDate(arrivalDate);

        return response;
    }
}
