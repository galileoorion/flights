package com.travix.medusa.busyflights.controller;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.service.busyflights.BusyFlightsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
public class FlightsController {

    private BusyFlightsService busyFlightsService;

    public FlightsController(BusyFlightsService busyFlightsService) {
        this.busyFlightsService = busyFlightsService;
    }

    @PostMapping("/search")
    public List<BusyFlightsResponse> search(@Valid @RequestBody BusyFlightsRequest request){
        return busyFlightsService.search(request);
    }
}
