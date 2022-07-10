package com.travix.medusa.busyflights.utils;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MathUtilsTest {

    @Test
    void roundTo2Decimals_roundsDown_when3DigitLessThan5() {

        double actual = MathUtils.roundTo2Decimals(1000.1111);
        assertThat(actual).isEqualTo(1000.11);
    }

    @Test
    void roundTo2Decimals_roundsUpTowardsTheEvenNeighbour_when3DigitIs5_1() {

        double actual = MathUtils.roundTo2Decimals(1000.1150);
        assertThat(actual).isEqualTo(1000.12);
    }

    @Test
    void roundTo2Decimals_roundsUpTowardsTheEvenNeighbour_when3DigitIs5_2() {

        double actual = MathUtils.roundTo2Decimals(1000.1250);
        assertThat(actual).isEqualTo(1000.13);
    }

    @Test
    void roundTo2Decimals_roundsUp_when3DigitGreaterThan5() {

        double actual = MathUtils.roundTo2Decimals(1000.1151);
        assertThat(actual).isEqualTo(1000.12);
    }
}