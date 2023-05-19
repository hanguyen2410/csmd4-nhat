package com.cg.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CartItemReqDTO implements Validator {

    private String productId;
    private String quantity;


    @Override
    public boolean supports(Class<?> clazz) {
        return CartItemReqDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CartItemReqDTO cartItemReqDTO = (CartItemReqDTO) target;
        String productId = cartItemReqDTO.getProductId();
        String quantity = cartItemReqDTO.getQuantity();

        if (productId.length() == 0) {
            errors.rejectValue("productId", "productId.length", "Mã sản phẩm không được trống");
        }
        else if (!productId.matches("\\d+")) {
            errors.rejectValue("productId", "productId.matches", "Mã sản phẩm phải là ký tự số");
        }

        if (quantity.length() == 0) {
            errors.rejectValue("quantity", "quantity.length", "Số lượng không được trống");
        }
        else if (!quantity.matches("\\d+")) {
            errors.rejectValue("quantity", "quantity.matches", "Số lượng là ký tự số");
        }
    }
}
