package com.project.bookStore.dtos;

import com.project.bookStore.common.dto.BaseDTO;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartDTO extends BaseDTO {

    private String name;
    private int quantity;
    private double totalPrice;

    private  UserDTO userDTO;

    private ProductDTO product;
}
