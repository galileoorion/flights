package com.travix.medusa.busyflights.service;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;

import java.util.List;
import java.util.stream.Collectors;

public class SupplierService<Request, Response> implements AirlineService {
    public ExternalService<Request, Response> externalService;
    public RequestMapper<Request> requestMapper;
    public ResponseMapper<Response> responseMapper;

    public SupplierService(ExternalService<Request, Response> externalService, RequestMapper<Request> requestMapper, ResponseMapper<Response> responseMapper) {
        this.externalService = externalService;
        this.requestMapper = requestMapper;
        this.responseMapper = responseMapper;
    }

    @Override
    public List<BusyFlightsResponse> search(BusyFlightsRequest busyFlightsRequest) {
        Request request = requestMapper.map(busyFlightsRequest);
        List<Response> responses = externalService.search(request);
        return responses.stream().map(responseMapper::map).collect(Collectors.toList());
    }
}
