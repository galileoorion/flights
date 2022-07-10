package com.travix.medusa.busyflights.service.crazyair;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.service.AirlineService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CrazyAirService implements AirlineService {

    private CrazyAirExternalService externalService;
    private CrazyAirRequestMapper requestMapper;
    private CrazyAirResponseMapper responseMapper;

    public CrazyAirService(CrazyAirExternalService externalService, CrazyAirRequestMapper requestMapper, CrazyAirResponseMapper responseMapper) {
        this.externalService = externalService;
        this.requestMapper = requestMapper;
        this.responseMapper = responseMapper;
    }

    @Override
    public List<BusyFlightsResponse> search(BusyFlightsRequest request) {
        CrazyAirRequest crazyAirRequest = requestMapper.map(request);
        List<CrazyAirResponse> crazyAirResponses = externalService.search(crazyAirRequest);
        return crazyAirResponses.stream().map(responseMapper::map).collect(Collectors.toList());
    }
}
