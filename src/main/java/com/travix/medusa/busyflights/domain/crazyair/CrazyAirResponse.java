package com.travix.medusa.busyflights.domain.crazyair;

import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class CrazyAirResponse {

    public static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    private String airline;
    private double price;
    private String cabinclass;
    private String departureAirportCode;
    private String destinationAirportCode;
    private String departureDate;
    private String arrivalDate;

    public String getAirline() {
        return airline;
    }

    public void setAirline(final String airline) {
        this.airline = airline;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(final double price) {
        this.price = price;
    }

    public String getCabinclass() {
        return cabinclass;
    }

    public void setCabinclass(final String cabinclass) {
        this.cabinclass = cabinclass;
    }

    public String getDepartureAirportCode() {
        return departureAirportCode;
    }

    public void setDepartureAirportCode(final String departureAirportCode) {
        this.departureAirportCode = departureAirportCode;
    }

    public String getDestinationAirportCode() {
        return destinationAirportCode;
    }

    public void setDestinationAirportCode(final String destinationAirportCode) {
        this.destinationAirportCode = destinationAirportCode;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(final String departureDate) {
        this.departureDate = departureDate;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(final String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CrazyAirResponse response = (CrazyAirResponse) o;
        return Double.compare(response.price, price) == 0 && Objects.equals(airline, response.airline) && Objects.equals(cabinclass, response.cabinclass) && Objects.equals(departureAirportCode, response.departureAirportCode) && Objects.equals(destinationAirportCode, response.destinationAirportCode) && Objects.equals(departureDate, response.departureDate) && Objects.equals(arrivalDate, response.arrivalDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(airline, price, cabinclass, departureAirportCode, destinationAirportCode, departureDate, arrivalDate);
    }
}
