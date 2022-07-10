package com.travix.medusa.busyflights.service.toughjet;

import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import com.travix.medusa.busyflights.service.ExternalService;
import com.travix.medusa.busyflights.service.RequestMapper;
import com.travix.medusa.busyflights.service.ResponseMapper;
import com.travix.medusa.busyflights.service.SupplierService;
import org.springframework.stereotype.Service;

@Service
public class ToughJetService extends SupplierService<ToughJetRequest, ToughJetResponse> {

    public ToughJetService(ExternalService<ToughJetRequest, ToughJetResponse> externalService, RequestMapper<ToughJetRequest> requestMapper, ResponseMapper<ToughJetResponse> responseMapper) {
        super(externalService, requestMapper, responseMapper);
    }
}
