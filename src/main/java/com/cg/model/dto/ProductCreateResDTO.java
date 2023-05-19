package com.cg.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductCreateResDTO {

    private Long id;
    private String title;
    private BigDecimal price;
    private String description;
    private String unit;
    private String categoryTitle;
    private ProductAvatarDTO avatar;

}
