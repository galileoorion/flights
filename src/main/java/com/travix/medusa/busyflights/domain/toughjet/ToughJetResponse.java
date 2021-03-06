package com.travix.medusa.busyflights.domain.toughjet;

import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class ToughJetResponse {

    public static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ISO_INSTANT;

    private String carrier;
    private double basePrice;
    private double tax;
    private double discount;
    private String departureAirportName;
    private String arrivalAirportName;
    private String outboundDateTime;
    private String inboundDateTime;

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(final String carrier) {
        this.carrier = carrier;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(final double basePrice) {
        this.basePrice = basePrice;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(final double tax) {
        this.tax = tax;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(final double discount) {
        this.discount = discount;
    }

    public String getDepartureAirportName() {
        return departureAirportName;
    }

    public void setDepartureAirportName(final String departureAirportName) {
        this.departureAirportName = departureAirportName;
    }

    public String getArrivalAirportName() {
        return arrivalAirportName;
    }

    public void setArrivalAirportName(final String arrivalAirportName) {
        this.arrivalAirportName = arrivalAirportName;
    }

    public String getOutboundDateTime() {
        return outboundDateTime;
    }

    public void setOutboundDateTime(final String outboundDateTime) {
        this.outboundDateTime = outboundDateTime;
    }

    public String getInboundDateTime() {
        return inboundDateTime;
    }

    public void setInboundDateTime(final String inboundDateTime) {
        this.inboundDateTime = inboundDateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToughJetResponse that = (ToughJetResponse) o;
        return Double.compare(that.basePrice, basePrice) == 0 && Double.compare(that.tax, tax) == 0 && Double.compare(that.discount, discount) == 0 && Objects.equals(carrier, that.carrier) && Objects.equals(departureAirportName, that.departureAirportName) && Objects.equals(arrivalAirportName, that.arrivalAirportName) && Objects.equals(outboundDateTime, that.outboundDateTime) && Objects.equals(inboundDateTime, that.inboundDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carrier, basePrice, tax, discount, departureAirportName, arrivalAirportName, outboundDateTime, inboundDateTime);
    }
}
