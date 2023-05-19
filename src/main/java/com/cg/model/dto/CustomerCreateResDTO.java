package com.cg.model.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CustomerCreateResDTO {

    private Long id;
    private String username;
    private String fullName;
    private String phone;
}
