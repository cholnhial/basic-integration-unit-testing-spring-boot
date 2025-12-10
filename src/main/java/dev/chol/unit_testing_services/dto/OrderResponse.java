package dev.chol.unit_testing_services.dto;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public record OrderResponse(Long id, ZonedDateTime createdAt, Long productId, Integer qty, String discountCode, String productName, BigDecimal productPrice, String productSku) {

    public static OrderResponse orderToDto(dev.chol.unit_testing_services.domain.Order order) {
        return new OrderResponse(
                order.getId(),
                order.getCreatedAt(),
                order.getProduct().getId(),
                order.getQty(),
                order.getDiscountCode(),
                order.getProduct().getName(),
                order.getProduct().getPrice(),
                order.getProduct().getSku()
        );
    }
}
