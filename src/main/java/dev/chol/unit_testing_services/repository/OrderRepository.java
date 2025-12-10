package dev.chol.unit_testing_services.repository;

import dev.chol.unit_testing_services.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    // ...existing code...
}

