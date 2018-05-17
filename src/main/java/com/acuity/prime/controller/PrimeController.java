package com.acuity.prime.controller;

import com.acuity.prime.exception.PrimeGenerationException;
import com.acuity.prime.model.PrimeResponse;
import com.acuity.prime.service.PrimeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * Class exposes service to generate prime number.
 * <p>
 * Created by Amit on 15/05/2018.
 */
@RestController
@RequestMapping("/primes")
@Validated
public class PrimeController {

    private static final String SIEVE = "sieve";
    private static final String MILLER_RABIN = "millerRabin";
    private static final String JSON = "json";
    private static final String XML = "xml";

    private static final Logger LOGGER = LoggerFactory.getLogger(PrimeController.class);

    @Inject
    @Named("sievePrimeService")
    private PrimeService sievePrimeService;

    @Inject
    @Named("bigIntegerPrimeService")
    private PrimeService bigIntegerPrimeService;

    @Inject
    @Named("jsonMapper")
    private ObjectMapper jsonMapper;

    @Inject
    @Named("xmlMapper")
    private XmlMapper xmlMapper;

    /**
     * Get prime numbers until Initial path parameter
     *
     * @param initial value
     * @param algo    algorithm
     * @param output  type of output JSON or XML
     * @return
     */
    @GetMapping(value = "/{initial}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> generatePrime(@Valid
                                           @Min(value = 3)
                                           @Max(value = 10000000)
                                           @PathVariable("initial")
                                           final Integer initial,
                                           @RequestParam(required = false, defaultValue = SIEVE) final String algo,
                                           @RequestParam(required = false, defaultValue = JSON) final String output) {

        LOGGER.info("Generate prime number starts");
        PrimeResponse primeResponse = null;
        String response = null;
        try {
            switch (algo) {
                case MILLER_RABIN:
                    primeResponse = bigIntegerPrimeService.generatePrime(initial);
                    break;
                case SIEVE:
                default:
                    primeResponse = sievePrimeService.generatePrime(initial);
                    break;
            }

            switch (output) {
                case XML:
                    response = xmlMapper.writeValueAsString(primeResponse);
                    break;
                case JSON:
                default:
                    response = jsonMapper.writeValueAsString(primeResponse);
                    break;
            }

        } catch (PrimeGenerationException e) {
            new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (JsonProcessingException e) {
            new ResponseEntity<>("Unable to process your request.", HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            LOGGER.info("Generate prime number ends");
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
