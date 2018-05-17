package com.acuity.prime.model;

/**
 * Class contains response on generated Prime numbers based on initial value.
 * <p>
 * Created by Amit on 15/05/2018.
 */
public class PrimeResponse {

    private final Integer initial;

    private final Integer[] primes;

    public PrimeResponse() {
        initial = null;
        primes = null;
    }

    public PrimeResponse(Integer initial, Integer[] primes) {
        this.initial = initial;
        this.primes = primes;
    }

    /**
     * Get initial value
     *
     * @return Integer
     */
    public Integer getInitial() {
        return initial;
    }

    /**
     * Get prime numbers
     *
     * @return Array of prime numbers
     */
    public Integer[] getPrimes() {
        return primes;
    }
}
