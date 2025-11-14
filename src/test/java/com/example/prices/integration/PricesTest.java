package com.example.prices.integration;

import com.example.prices.PricesApplication;
import com.company.prices.api.model.PriceResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = PricesApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PricesIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String baseUrl;

    @BeforeEach
    void setUp() {
        baseUrl = "http://localhost:" + port + "/prices";
    }

    @Test
    void testFirstCase() {
        // Given
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0, 0);
        Integer productId = 35455;
        Integer brandId = 1;

        // When
        ResponseEntity<PriceResponse> response = restTemplate.getForEntity(
                baseUrl + "?applicationDate={applicationDate}&productId={productId}&brandId={brandId}",
                PriceResponse.class, applicationDate, productId, brandId);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());

        PriceResponse price = response.getBody();
        assertEquals(35455, price.getProductId());
        assertEquals(1, price.getBrandId());
        assertEquals(1, price.getPriceList());
        assertEquals(35.50, price.getPrice());
        assertEquals(0, price.getPriority());
        assertEquals("EUR", price.getCurrency());
    }

    @Test
    void testSecondCase() {
        // Given
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 16, 0, 0);
        Integer productId = 35455;
        Integer brandId = 1;

        // When
        ResponseEntity<PriceResponse> response = restTemplate.getForEntity(
                baseUrl + "?applicationDate={applicationDate}&productId={productId}&brandId={brandId}",
                PriceResponse.class, applicationDate, productId, brandId);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());

        PriceResponse price = response.getBody();
        assertEquals(2, price.getPriceList());
        assertEquals(25.45, price.getPrice());
        assertEquals(1, price.getPriority());
    }

    @Test
    void testThirdCase() {
        // Given
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 21, 0, 0);
        Integer productId = 35455;
        Integer brandId = 1;

        // When
        ResponseEntity<PriceResponse> response = restTemplate.getForEntity(
                baseUrl + "?applicationDate={applicationDate}&productId={productId}&brandId={brandId}",
                PriceResponse.class, applicationDate, productId, brandId);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());

        PriceResponse price = response.getBody();
        assertEquals(1, price.getPriceList());
        assertEquals(35.50, price.getPrice());
        assertEquals(0, price.getPriority());
    }

    @Test
    void testFourthCase() {
        // Given
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 15, 10, 0, 0);
        Integer productId = 35455;
        Integer brandId = 1;

        // When
        ResponseEntity<PriceResponse> response = restTemplate.getForEntity(
                baseUrl + "?applicationDate={applicationDate}&productId={productId}&brandId={brandId}",
                PriceResponse.class, applicationDate, productId, brandId);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());

        PriceResponse price = response.getBody();
        assertEquals(3, price.getPriceList());
        assertEquals(30.50, price.getPrice());
        assertEquals(1, price.getPriority());
    }

    @Test
    void testFifthCase() {
        // Given
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 16, 21, 0, 0);
        Integer productId = 35455;
        Integer brandId = 1;

        // When
        ResponseEntity<PriceResponse> response = restTemplate.getForEntity(
                baseUrl + "?applicationDate={applicationDate}&productId={productId}&brandId={brandId}",
                PriceResponse.class, applicationDate, productId, brandId);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());

        PriceResponse price = response.getBody();
        assertEquals(4, price.getPriceList());
        assertEquals(38.95, price.getPrice());
        assertEquals(1, price.getPriority());
    }

    @Test
    void testNotFound() {
        // Given - Fecha fuera de rangos
        LocalDateTime applicationDate = LocalDateTime.of(2021, 1, 1, 10, 0, 0);
        Integer productId = 35455;
        Integer brandId = 1;

        // When
        ResponseEntity<PriceResponse> response = restTemplate.getForEntity(
                baseUrl + "?applicationDate={applicationDate}&productId={productId}&brandId={brandId}",
                PriceResponse.class, applicationDate, productId, brandId);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}