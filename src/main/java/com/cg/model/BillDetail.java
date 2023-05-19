package com.cg.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "bill_details")
public class BillDetail extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productTitle;

    @Column(precision = 10, scale = 0, nullable = false)
    private BigDecimal price;

    private Integer quantity;

    @Column(precision = 12, scale = 0, nullable = false)
    private BigDecimal amount;


    @ManyToOne
    @JoinColumn(name = "bill_id", referencedColumnName = "id", nullable = false)
    private Bill bill;
}
