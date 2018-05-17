package com.acuity.prime.service;

import com.acuity.prime.exception.PrimeGenerationException;
import com.acuity.prime.model.PrimeResponse;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit test of Sieve algorithm based Prime Service
 * <p>
 * Created by Amit on 17/05/2018.
 */

public class SievePrimeServiceTest {

    private PrimeService primeService = new SievePrimeService();

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void whenInitialIsLessThanThreeThenAnExceptionIsThrown() throws PrimeGenerationException {
        expectedException.expect(PrimeGenerationException.class);
        expectedException.expectMessage("Invalid input");
        primeService.generatePrime(-2);
    }

    @Test
    public void whenInitialIsThreeThenPrimeNumberIsTwo() throws PrimeGenerationException {
        //When
        PrimeResponse primeResponse = primeService.generatePrime(3);

        //Then
        assertThat(primeResponse.getInitial()).isEqualTo(3);
        assertThat(primeResponse.getPrimes()).contains(2);
    }

    @Test
    public void whenInitialIsHundredThenItGivesAllPrimes() throws PrimeGenerationException {
        //When
        PrimeResponse primeResponse = primeService.generatePrime(100);

        //Then
        assertThat(primeResponse.getInitial()).isEqualTo(100);
        assertThat(primeResponse.getPrimes()).contains(
                2, 3, 5, 7, 11, 13,
                17, 19, 23, 29, 31, 37,
                41, 43, 47, 53, 59, 61,
                67, 71, 73, 79, 83, 89,
                97);
    }

    @Test
    public void whenInitialIsHundredThenItDoesNotInclude101() throws PrimeGenerationException {
        //When
        PrimeResponse primeResponse = primeService.generatePrime(100);

        //Then
        assertThat(primeResponse.getInitial()).isEqualTo(100);
        assertThat(primeResponse.getPrimes()).doesNotContain(101);
    }
}