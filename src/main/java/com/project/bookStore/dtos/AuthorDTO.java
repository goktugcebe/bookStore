package com.project.bookStore.dtos;

import com.project.bookStore.common.dto.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthorDTO extends BaseDTO {
    @NotBlank(message = "Enter your last name")
    private String name;

    private List<ProductDTO> products;
}
