package dev.chol.unit_testing_services.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.chol.unit_testing_services.dto.OrderRequest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderControllerIT {
    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15").waitingFor(Wait.defaultWaitStrategy());

    @LocalServerPort
    private Integer port;

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost:" + port;
    }
    
    @Test
    public void makeOrder_ShouldCreateOrderSuccessfully() throws JsonProcessingException {
        
        ObjectMapper mapper = new ObjectMapper();
        OrderRequest orderRequest = new OrderRequest("SKU001", 2, null);
        given()
                .contentType(ContentType.JSON)
                .body(mapper.writeValueAsString(orderRequest))
                .when()
                .post("/orders")
                .then()
                .statusCode(200)
                .body("id", notNullValue())
                .body("productSku", equalTo("SKU001"))
                .body("qty", equalTo(2)); 
    }
    
    @Test
    public void makeOrder_thenGetCreatedOrderById_ShouldReturnOrder() throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        OrderRequest orderRequest = new OrderRequest("SKU002", 3, null);

        // Create Order
        Integer orderId =
                given()
                        .contentType(ContentType.JSON)
                        .body(mapper.writeValueAsString(orderRequest))
                        .when()
                        .post("/orders")
                        .then()
                        .statusCode(200)
                        .extract()
                        .path("id");

        // Retrieve Order by ID
        given()
                .when()
                .get("/orders/{id}", orderId)
                .then()
                .statusCode(200)
                .body("id", equalTo(orderId));
    }
}
