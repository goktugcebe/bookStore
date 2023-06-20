package com.project.bookStore.dtos;

import com.project.bookStore.common.dto.BaseDTO;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthorDTO extends BaseDTO {
    @NotBlank(message = "Enter your last name")
    private String name;

    private List<ProductDTO> products;
}
