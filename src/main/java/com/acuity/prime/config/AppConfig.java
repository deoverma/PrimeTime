package com.acuity.prime.config;

import com.acuity.prime.service.BigIntegerPrimeService;
import com.acuity.prime.service.PrimeService;
import com.acuity.prime.service.SievePrimeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * Application config to enable caching and Prime services
 * Created by Amit on 16/05/2018.
 */
@Configuration
@EnableCaching
public class AppConfig {

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("primes");
    }

    @Bean(name = "sievePrimeService")
    public PrimeService sievePrimeService() {
        return new SievePrimeService();
    }

    @Bean(name = "bigIntegerPrimeService")
    public PrimeService bigIntegerPrimeService() {
        return new BigIntegerPrimeService();
    }

    @Bean(name = "xmlMapper")
    public XmlMapper xmlMapper() {
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
        return xmlMapper;
    }

    @Bean(name = "jsonMapper")
    @Primary
    public ObjectMapper jsonMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        return objectMapper;
    }
}
