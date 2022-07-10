package com.travix.medusa.busyflights.service.busyflights;

import com.travix.medusa.busyflights.concurrent.CompletableFutureFlights;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.service.AirlineService;
import com.travix.medusa.busyflights.service.crazyair.CrazyAirService;
import com.travix.medusa.busyflights.service.toughjet.ToughJetService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class BusyFlightsService implements AirlineService {

    private CrazyAirService crazyAirService;
    private ToughJetService toughJetService;

    public BusyFlightsService(CrazyAirService crazyAirService, ToughJetService toughJetService) {
        this.crazyAirService = crazyAirService;
        this.toughJetService = toughJetService;
    }

    public List<BusyFlightsResponse> search(BusyFlightsRequest request) {
        return Stream.of(
                        CompletableFutureFlights.supplyAsyncThenHandle(() -> crazyAirService.search(request)),
                        CompletableFutureFlights.supplyAsyncThenHandle(() -> toughJetService.search(request))
                ).map(CompletableFuture::join)
                .reduce(new ArrayList<>(), (a, b) -> {
                    a.addAll(b);
                    return a;
                })
                .stream()
                .sorted(Comparator.comparing(BusyFlightsResponse::getFare))
                .collect(Collectors.toList());
    }
}
