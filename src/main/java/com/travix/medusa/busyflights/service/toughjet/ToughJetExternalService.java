package com.travix.medusa.busyflights.service.toughjet;

import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import com.travix.medusa.busyflights.service.ExternalService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToughJetExternalService implements ExternalService<ToughJetRequest, ToughJetResponse> {
    @Override
    public List<ToughJetResponse> search(ToughJetRequest toughJetRequest) {
        return List.of(new ToughJetResponse());
    }
}
