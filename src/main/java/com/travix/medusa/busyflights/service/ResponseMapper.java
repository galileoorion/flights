package com.travix.medusa.busyflights.service;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;

public interface ResponseMapper<Response> {
    BusyFlightsResponse map(Response response);
}
