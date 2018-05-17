package com.acuity.prime.service;

import com.acuity.prime.exception.PrimeGenerationException;
import com.acuity.prime.model.PrimeResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.stream.IntStream;

/**
 * PrimeService implementation of Sieve algorithm, which is fast algorithm to generate Prime numbers.
 * <p>
 * <p>
 * <p>
 * Created by Amit on 15/05/2018.
 */
@Service
public class SievePrimeService implements PrimeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SievePrimeService.class);

    @Override
    @Cacheable("primes")
    public PrimeResponse generatePrime(final Integer initial) throws PrimeGenerationException {
        LOGGER.info("Start generating prime using Sieve method");
        if (initial <= 2) {
            throw new PrimeGenerationException("Invalid input");
        }

        boolean[] sieve = new boolean[initial + 1];
        int limit = (int) Math.sqrt(initial);
        IntStream.range(0, sieve.length)
                .parallel()
                .forEach(i -> sieve[i] = true);

        for (int i = 2; i <= limit; i++) {
            if (sieve[i]) {
                for (int j = i * 2; j <= initial; j = j + i) {
                    sieve[j] = false;
                }
            }
        }

        Integer[] primes = IntStream.range(2, sieve.length)
                .parallel()
                .filter(i -> sieve[i])
                .map(i -> i)
                .boxed()
                .toArray(Integer[]::new);

        LOGGER.info("End generating prime using Sieve method");

        return new PrimeResponse(initial, primes);
    }
}
