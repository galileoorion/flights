package com.travix.medusa.busyflights.concurrent;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class CompletableFutureFlights extends CompletableFuture<BusyFlightsResponse> {
    private static final Logger LOGGER = LoggerFactory.getLogger(CompletableFutureFlights.class);
    public static CompletableFuture<List<BusyFlightsResponse>> supplyAsyncThenHandle(Supplier<List<BusyFlightsResponse>> supplier) {
        return CompletableFuture.supplyAsync(supplier)
                .handle((response, throwable) -> {
                            if (throwable != null) {
                                LOGGER.warn("Search results could not be retrieved", throwable);
                                return new ArrayList<>();
                            } else {
                                return response;
                            }
                        }
                );
    }
}
