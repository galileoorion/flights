package com.travix.medusa.busyflights.utils;

import org.junit.jupiter.api.Test;

import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;

class DateTimeUtilsTest {

    @Test
    void convertResponseDateTime_fromIsoLocalDateTime_toIsoDateTime() {
        //
        String date = "2021-12-03T10:15:30";

        // when
        String actual = DateTimeUtils.convertResponseDateTime(date, DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        // then
        assertThat(actual).isEqualTo(date);
    }

    @Test
    void convertResponseDateTime_fromIsoInstant_toIsoDateTime() {
        //
        String date = "2021-12-03T10:15:30Z";

        // when
        String actual = DateTimeUtils.convertResponseDateTime(date, DateTimeFormatter.ISO_INSTANT);

        // then
        String expected = "2021-12-03T10:15:30";
        assertThat(actual).isEqualTo(expected);
    }
}