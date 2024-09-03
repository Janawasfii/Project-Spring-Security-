package com.example.projectsecurity.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerDTO {

    @NotEmpty(message = "Username cannot be empty")
   // @Size(min=5, max=11)
    private String username;


    @NotEmpty(message = "Password cannot be empty")
   // @Size(min=6)
    private String password;


    @NotEmpty(message = "Name cannot be empty")
   // @Size(min=2, max=20)
    private String name;

    @Email
    private String email;

   // @NotEmpty(message = "Role cannot be empty")
   // @Pattern(regexp="^(ADMIN|CUSTOMER|EMPLOYEE)$",message = "Only 3 options(Admin or Customer or Employee)")
    private String role;

    @NotEmpty(message ="Phone number cannot be null")
    //@Pattern(regexp = "^(05|0)[0-9]{8}$")
    private String phoneNumber;
}
