package com.acuity.prime.controller;

import com.acuity.prime.service.PrimeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.inject.Inject;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Unit Test of Prime Controller
 * <p>
 * Created by Amit on 16/05/2018.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(value = PrimeController.class, secure = false)
public class PrimeControllerTest {

    @Inject
    MockMvc mockMvc;

    @MockBean(name = "sievePrimeService")
    private PrimeService sieveService;

    @MockBean(name = "bigIntegerPrimeService")
    private PrimeService bigIntegerService;

    @MockBean(name = "jsonMapper")
    private ObjectMapper jsonMapper;

    @MockBean(name = "xmlMapper")
    private XmlMapper xmlMapper;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void whenInitialPathVariableIsLessThanTwoThenServiceReturnsBadRequest() throws Exception {
        //Given
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/primes/2");

        //When
        mockMvc.perform(requestBuilder)

                //Then
                .andExpect(status().isBadRequest());
    }

    @Test
    public void whenInitialPathVariableIsMoreThanTenMillionThenServiceReturnsBadRequest() throws Exception {
        //Given
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/primes/10000001");

        //When
        mockMvc.perform(requestBuilder)

                //Then
                .andExpect(status().isBadRequest());

    }

    @Test
    public void whenRequestParamAlgoIsSieveThenSieveServiceUsed() throws Exception {
        //Given
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/primes/10?algo=sieve");

        //When
        mockMvc.perform(requestBuilder);

        //Then
        verify(sieveService, atLeastOnce()).generatePrime(anyInt());
    }

    @Test
    public void whenRequestParamAlgoIsMillerThenBigIntegerServiceUsed() throws Exception {
        //Given
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/primes/10?algo=millerRabin");

        //When
        mockMvc.perform(requestBuilder);

        //Then
        verify(bigIntegerService, atLeastOnce()).generatePrime(anyInt());
    }

    @Test
    public void whenRequestParamAlgoIsInvalidThenBigIntegerServiceUsed() throws Exception {
        //Given
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/primes/10?algo=invalid");

        //When
        mockMvc.perform(requestBuilder);

        //Then
        verify(sieveService, atLeastOnce()).generatePrime(anyInt());
    }

    @Test
    public void whenRequestParamOutputIsJsonThenJsonMapperIsUsed() throws Exception {
        //Given
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/primes/10?output=json");

        //When
        mockMvc.perform(requestBuilder);

        //Then
        verify(jsonMapper, atLeastOnce()).writeValueAsString(any());
    }

    @Test
    public void whenRequestParamOutputIsXMLThenXMLMapperIsUsed() throws Exception {
        //Given
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/primes/10?output=xml");

        //When
        mockMvc.perform(requestBuilder);

        //Then
        verify(xmlMapper, atLeastOnce()).writeValueAsString(any());
    }

    @Test
    public void whenRequestParamOutputIsInvalidThenJsonMapperIsUsed() throws Exception {
        //Given
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/primes/10?output=invalid");

        //When
        mockMvc.perform(requestBuilder);

        //Then
        verify(jsonMapper, atLeastOnce()).writeValueAsString(any());
    }
}