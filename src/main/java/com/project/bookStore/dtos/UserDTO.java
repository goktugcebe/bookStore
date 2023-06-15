package com.project.bookStore.dtos;

import com.project.bookStore.common.dto.BaseDTO;
import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

//@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserDTO extends BaseDTO {
    @NotBlank(message = "Enter your email")
    @Email(message = "Enter a valid email address")
    private String email;
    @NotBlank(message = "Enter your password")
    @Size(min = 6, message = "Passwords must be at least 6 characters")
    private String password;
    @NotBlank(message = "Re-enter your password")
    private String rpassword;
    @NotBlank(message = "Enter your first name")
    private String firstname;
    @NotBlank(message = "Enter your last name")
    private String lastname;
    @NotEmpty(message = "Please enter valid phone number.")
    @Size(min = 9,max = 9, message = "Phone Number must be 9 digits")
    private String phoneNumber;
//    @NotBlank(message = "Please enter gender.")

    private String address;
    private String gender;

    private CartDTO cartDTO;

}
