package com.karbo;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class BottleTest {

    @Test
    public void amountShallNotBeNegative() {
        //Given
        Bottle bottle = new Bottle(Capacity.FIVE);
        //When
        bottle.setAmount(-2);
        //Then
        assertThat(bottle.getAmount(), is(0));
    }

    @Test
    public void amountShallNotBeMoreThanCapacity() {
        //Given
        Bottle bottle = new Bottle(Capacity.FIVE);
        //When
        bottle.setAmount(6);
        //Then
        assertThat(bottle.getAmount(), is(5));
    }

}