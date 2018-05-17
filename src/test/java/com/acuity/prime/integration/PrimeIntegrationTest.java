package com.acuity.prime.integration;

import com.acuity.prime.model.PrimeResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Integration Test fo Prime API
 * <p>
 * Created by Amit on 17/05/2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PrimeIntegrationTest {

    @Inject
    private TestRestTemplate restTemplate;

    @Test
    public void whenPrimeRequestIsValidThenItReturnsResponseCode200() {
        //when
        ResponseEntity<PrimeResponse> responseEntity = restTemplate.getForEntity("/primes/15", PrimeResponse.class);
        //Then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().getInitial()).isEqualTo(15);
        assertThat(responseEntity.getBody().getPrimes()).contains(2, 3, 5, 7, 11, 13);
    }

    @Test
    public void whenPrimeRequestIsInvalidThenItReturnsResponseCode400() {
        //when
        ResponseEntity<HashMap> responseEntity = restTemplate.getForEntity("/primes/-1", HashMap.class);
        //Then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(responseEntity.getBody().size()).isEqualTo(1);
    }
}
