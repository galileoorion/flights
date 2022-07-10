package com.travix.medusa.busyflights.domain.busyflights;

import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class BusyFlightsResponse {

    public static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ISO_DATE_TIME;

    private String airline;
    private String supplier;
    private double fare;
    private String departureAirportCode;
    private String destinationAirportCode;
    private String departureDate;
    private String arrivalDate;

    public String getSupplier() {
        return supplier;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public double getFare() {
        return fare;
    }

    public void setFare(double fare) {
        this.fare = fare;
    }

    public String getDepartureAirportCode() {
        return departureAirportCode;
    }

    public void setDepartureAirportCode(String departureAirportCode) {
        this.departureAirportCode = departureAirportCode;
    }

    public String getDestinationAirportCode() {
        return destinationAirportCode;
    }

    public void setDestinationAirportCode(String destinationAirportCode) {
        this.destinationAirportCode = destinationAirportCode;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BusyFlightsResponse response = (BusyFlightsResponse) o;
        return Double.compare(response.fare, fare) == 0 && Objects.equals(supplier, response.supplier) && Objects.equals(departureAirportCode, response.departureAirportCode) && Objects.equals(destinationAirportCode, response.destinationAirportCode) && Objects.equals(departureDate, response.departureDate) && Objects.equals(arrivalDate, response.arrivalDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(supplier, fare, departureAirportCode, destinationAirportCode, departureDate, arrivalDate);
    }

    @Override
    public String toString() {
        return "BusyFlightsResponse{" +
                "supplier='" + supplier + '\'' +
                ", fare=" + fare +
                ", departureAirportCode='" + departureAirportCode + '\'' +
                ", destinationAirportCode='" + destinationAirportCode + '\'' +
                ", departureDate='" + departureDate + '\'' +
                ", arrivalDate='" + arrivalDate + '\'' +
                '}';
    }
}
