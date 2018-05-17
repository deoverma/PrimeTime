package com.acuity.prime.service;

import com.acuity.prime.exception.PrimeGenerationException;
import com.acuity.prime.model.PrimeResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.stream.IntStream;

/**
 * PrimeService implementation of BigInteger class, which internally implements
 * Miller-Rabin algorithm.
 * <p>
 * <p>
 * Created by Amit on 15/05/2018.
 */
@Service
public class BigIntegerPrimeService implements PrimeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SievePrimeService.class);

    @Override
    @Cacheable("primes")
    public PrimeResponse generatePrime(final Integer initial) throws PrimeGenerationException {
        LOGGER.info("Start generating using Miller-Rabin algorithm ");

        if (initial <= 2) {
            throw new PrimeGenerationException("Invalid input");
        }
        Integer[] primes = IntStream.range(2, initial)
                .parallel()
                .filter(i -> BigInteger.valueOf(i).isProbablePrime(100))
                .boxed()
                .toArray(Integer[]::new);

        LOGGER.info("End generating using Miller-Rabin algorithm ");
        return new PrimeResponse(initial, primes);
    }
}
