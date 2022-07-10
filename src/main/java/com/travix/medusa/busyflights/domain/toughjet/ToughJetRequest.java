package com.travix.medusa.busyflights.domain.toughjet;

import java.util.Objects;

public class ToughJetRequest {

    private String from;
    private String to;
    private String outboundDate;
    private String inboundDate;
    private int numberOfAdults;

    public String getFrom() {
        return from;
    }

    public void setFrom(final String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(final String to) {
        this.to = to;
    }

    public String getOutboundDate() {
        return outboundDate;
    }

    public void setOutboundDate(final String outboundDate) {
        this.outboundDate = outboundDate;
    }

    public String getInboundDate() {
        return inboundDate;
    }

    public void setInboundDate(final String inboundDate) {
        this.inboundDate = inboundDate;
    }

    public int getNumberOfAdults() {
        return numberOfAdults;
    }

    public void setNumberOfAdults(final int numberOfAdults) {
        this.numberOfAdults = numberOfAdults;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToughJetRequest that = (ToughJetRequest) o;
        return numberOfAdults == that.numberOfAdults && Objects.equals(from, that.from) && Objects.equals(to, that.to) && Objects.equals(outboundDate, that.outboundDate) && Objects.equals(inboundDate, that.inboundDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to, outboundDate, inboundDate, numberOfAdults);
    }
}
