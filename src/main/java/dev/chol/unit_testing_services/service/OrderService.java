package dev.chol.unit_testing_services.service;

import dev.chol.unit_testing_services.domain.Order;
import dev.chol.unit_testing_services.repository.OrderRepository;
import dev.chol.unit_testing_services.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class OrderService {
    
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    
    
    @Transactional
    public Order makeOrderBySku(String sku, int qty) {
        var productOpt = productRepository.findBySku(sku);
        if (productOpt.isEmpty()) {
            throw new IllegalArgumentException("Product with SKU " + sku + " not found.");
        }
        var product = productOpt.get();
        if (product.getStock() <= 0) {
            throw new IllegalStateException("Product with SKU " + sku + " is out of stock.");
        }
        
        if (product.getStock() < qty) { 
            throw new IllegalStateException("Insufficient stock for product with SKU " + sku + ".");
        }
        
       Order order = new Order();
        order.setCreatedAt(ZonedDateTime.now());
       order.setProduct(product);
       order.setQty(qty);
       
       return this.orderRepository.save(order);
    }
    
    public Optional<Order> getOrderById(Long id) {
        return this.orderRepository.findById(id);
    }
}
