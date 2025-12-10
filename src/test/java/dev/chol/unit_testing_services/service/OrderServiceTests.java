package dev.chol.unit_testing_services.service;

import dev.chol.unit_testing_services.domain.Product;
import dev.chol.unit_testing_services.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for OrderService ignoring JPA dependencies
 */
@ExtendWith(MockitoExtension.class)
public class OrderServiceTests 
{
    
    // will create an instance of OrderService and inject the mocks into it
    @InjectMocks
    private OrderService orderService;
    
    // Will be injected into orderService
    @Mock
    private ProductRepository productRepository;
    
    @Test 
    public void givenProductOutOfStock_whenMakeOrderBySku_thenThrowIllegalStateException() {
        // Arrange
        String sku = "SKU1001";
        Mockito.when(productRepository.findBySku(sku)).thenReturn(Optional.of(new Product(1L,"Charger", BigDecimal.ZERO , 0, sku)));
         
         // Act && Assert
        assertThrows(IllegalStateException.class, () -> {
            orderService.makeOrderBySku(sku, 1);
        });
    }
    
    @Test
    public void givenInsufficientProductStock_whenMakeOrderBySku_thenThrowIllegalStateException() {
        // Arrange
        String sku = "SKU1002";
        Mockito.when(productRepository.findBySku(sku)).thenReturn(Optional.of(new Product(1L,"Headphones", BigDecimal.valueOf(3) , 2, sku)));
         
         // Act && Assert
        assertThrows(IllegalStateException.class, () -> {
            orderService.makeOrderBySku(sku, 5);
        });
    }
    
}
