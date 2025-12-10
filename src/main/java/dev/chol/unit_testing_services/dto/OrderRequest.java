package dev.chol.unit_testing_services.dto;

public record OrderRequest(String productSku, Integer qty, String discountCode) {
    
}
