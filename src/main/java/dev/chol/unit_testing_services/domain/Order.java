package dev.chol.unit_testing_services.domain;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.ZonedDateTime;

@Entity
@Table(name = "orders")
@Setter
@Getter
@EqualsAndHashCode(of = "id")
@ToString
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    private Product product;

    private Integer qty;

    @Column(name = "discount_code")
    private String discountCode;
    
    @Column(name = "created_at")
    private ZonedDateTime createdAt;
}
