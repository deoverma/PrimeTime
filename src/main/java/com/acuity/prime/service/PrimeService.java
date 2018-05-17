package com.acuity.prime.service;

import com.acuity.prime.exception.PrimeGenerationException;
import com.acuity.prime.model.PrimeResponse;

/**
 * Service provides service contract to implement Prime number algorithm
 * <p>
 * Created by Amit on 15/05/2018.
 */
public interface PrimeService {

    PrimeResponse generatePrime(final Integer initial) throws PrimeGenerationException;
}
