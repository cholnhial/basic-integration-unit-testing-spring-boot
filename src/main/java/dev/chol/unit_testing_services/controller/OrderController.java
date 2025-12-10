package dev.chol.unit_testing_services.controller;

import dev.chol.unit_testing_services.domain.Order;
import dev.chol.unit_testing_services.dto.OrderRequest;
import dev.chol.unit_testing_services.dto.OrderResponse;
import dev.chol.unit_testing_services.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    
    private final OrderService orderService;
    
    @PostMapping()
    public ResponseEntity<?> makeOrder(@RequestBody OrderRequest orderRequest) {
        Order order;
        
        try {
            order = orderService.makeOrderBySku(orderRequest.productSku(), orderRequest.qty());
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
        return ResponseEntity.ok(OrderResponse.orderToDto(order));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable Long id) {
        var orderOpt = orderService.getOrderById(id);
        if (orderOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(OrderResponse.orderToDto(orderOpt.get()));
    }
}
