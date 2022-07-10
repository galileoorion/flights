package com.travix.medusa.busyflights.service.toughjet;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import com.travix.medusa.busyflights.service.AirlineService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ToughJetService implements AirlineService {
    private ToughJetExternalService externalService;
    private ToughJetRequestMapper requestMapper;
    private ToughJetResponseMapper responseMapper;

    public ToughJetService(ToughJetExternalService externalService, ToughJetRequestMapper requestMapper, ToughJetResponseMapper responseMapper) {
        this.externalService = externalService;
        this.requestMapper = requestMapper;
        this.responseMapper = responseMapper;
    }

    @Override
    public List<BusyFlightsResponse> search(BusyFlightsRequest request) {
        ToughJetRequest toughJetRequest = requestMapper.map(request);
        List<ToughJetResponse> toughJetResponses = externalService.search(toughJetRequest);
        return toughJetResponses.stream().map(responseMapper::map).collect(Collectors.toList());
    }
}
