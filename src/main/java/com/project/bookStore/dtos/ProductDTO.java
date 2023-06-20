package com.project.bookStore.dtos;

import com.project.bookStore.common.dto.BaseDTO;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDTO extends BaseDTO {
    @NotBlank(message = "Enter your last name")
    @Size(min = 1, max = 50)
    private String name;
    @NotBlank(message = "Enter your last name")
    @Min(0)
    @Max(5)
    private int rating;
    @NotBlank(message = "Enter your last name")
    @Size(min = 1, max = 400)
    private String description;
    @NotBlank(message = "Enter your last name")
    @Min(1600)
    @Max(2023)
    private int releaseYear;
    @NotBlank(message = "Enter your last name")
    @Size(min = 1, max = 40)
    private String category;
    @Min(0)
    private double price;
    private int quantity;
    private String image;

    private int cartId;

    private AuthorDTO author;


}
