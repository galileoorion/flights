package com.travix.medusa.busyflights.service;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;

public interface RequestMapper<Request> {
    Request map(BusyFlightsRequest request);
}
