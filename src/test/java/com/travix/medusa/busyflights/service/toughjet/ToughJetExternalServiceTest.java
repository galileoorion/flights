package com.travix.medusa.busyflights.service.toughjet;

import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ToughJetExternalServiceTest {

    private ToughJetExternalService toughJetExternalService;

    @BeforeEach
    void setUp() {
        toughJetExternalService = new ToughJetExternalService();
    }

    @Test
    void search() {
        assertThat(toughJetExternalService.search(new ToughJetRequest()))
                .containsExactly(new ToughJetResponse());
    }
}