package com.travix.medusa.busyflights.service;

import java.util.List;

public interface ExternalService<Request, Response> {
    List<Response> search(Request request);
}
