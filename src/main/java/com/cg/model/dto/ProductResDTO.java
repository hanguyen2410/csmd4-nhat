package com.cg.model.dto;

import com.cg.model.ProductAvatar;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductResDTO {

    private Long id;
    private String title;
    private BigDecimal price;
    private String description;
    private String unit;
    private String categoryTitle;
    private ProductAvatarDTO avatar;

    public ProductResDTO(Long id, String title, BigDecimal price, String description, String unit, String categoryTitle, ProductAvatar avatar) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.description = description;
        this.unit = unit;
        this.categoryTitle = categoryTitle;
        this.avatar = avatar.toProductAvatarDTO();
    }
}
