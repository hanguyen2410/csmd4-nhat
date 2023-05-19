package com.cg.model;

import com.cg.model.dto.ProductCreateResDTO;
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
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(precision = 9, scale = 0, nullable = false)
    private BigDecimal price;

    private Long quantity;

    @ManyToOne
    @JoinColumn(name = "unit_id", referencedColumnName = "id", nullable = false)
    private Unit unit;

    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
    private Category category;

    @OneToOne
    @JoinColumn(name = "avatar_id", referencedColumnName = "id", nullable = false)
    private ProductAvatar avatar;

    public ProductCreateResDTO toProductCreateResDTO() {
        return new ProductCreateResDTO()
                .setId(id)
                .setTitle(title)
                .setPrice(price)
                .setDescription(description)
                .setUnit(unit.getTitle())
                .setCategoryTitle(category.getTitle())
                .setAvatar(avatar.toProductAvatarDTO())
                ;
    }
}
