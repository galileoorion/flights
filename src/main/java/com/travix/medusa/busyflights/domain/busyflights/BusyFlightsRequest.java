package com.travix.medusa.busyflights.domain.busyflights;

import javax.validation.constraints.*;
import java.util.Objects;

public class BusyFlightsRequest {

    @Size(min = 3, max = 3, message
            = "Origin must be a 3-letter IATA location code")
    @NotBlank
    private String origin;
    @Size(min = 3, max = 3, message
            = "Destination must be a 3-letter IATA location code")
    @NotBlank
    private String destination;
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}")
    @NotBlank
    private String departureDate;
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}")
    @NotBlank
    private String returnDate;
    @Min(1)
    @Max(4)
    private int numberOfPassengers;

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(final String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(final String destination) {
        this.destination = destination;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(final String departureDate) {
        this.departureDate = departureDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(final String returnDate) {
        this.returnDate = returnDate;
    }

    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }

    public void setNumberOfPassengers(final int numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BusyFlightsRequest that = (BusyFlightsRequest) o;
        return numberOfPassengers == that.numberOfPassengers && Objects.equals(origin, that.origin) && Objects.equals(destination, that.destination) && Objects.equals(departureDate, that.departureDate) && Objects.equals(returnDate, that.returnDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(origin, destination, departureDate, returnDate, numberOfPassengers);
    }
}
