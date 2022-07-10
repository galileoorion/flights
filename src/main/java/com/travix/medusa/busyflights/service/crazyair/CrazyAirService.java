package com.travix.medusa.busyflights.service.crazyair;

import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.service.ExternalService;
import com.travix.medusa.busyflights.service.RequestMapper;
import com.travix.medusa.busyflights.service.ResponseMapper;
import com.travix.medusa.busyflights.service.SupplierService;
import org.springframework.stereotype.Service;

@Service
public class CrazyAirService extends SupplierService<CrazyAirRequest, CrazyAirResponse> {

    public CrazyAirService(ExternalService<CrazyAirRequest, CrazyAirResponse> externalService, RequestMapper<CrazyAirRequest> requestMapper, ResponseMapper<CrazyAirResponse> responseMapper) {
        super( externalService, requestMapper,  responseMapper);
    }
}
