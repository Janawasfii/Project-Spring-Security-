package com.example.projectsecurity.DTO;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeDTO {

    private Integer user_id;

//    @NotEmpty(message = "Position cannot be empty")
    private String position;

//    @NotNull(message = "Salary cannot be null")
//    @Positive(message = "Salary should be positive number")
    private double salary;

//    @NotEmpty(message = "Username cannot be empty")
//    @Size(min=5, max=11)
    private String username;


//    @NotEmpty(message = "Password cannot be empty")
//    @Size(min=6)
    private String password;


//    @NotEmpty(message = "Name cannot be empty")
//    @Size(min=2, max=20)
    private String name;

    @Email
    private String email;

    @NotEmpty(message = "Role cannot be empty")
   // @Pattern(regexp="^(ADMIN|CUSTOMER|EMPLOYEE)$",message = "Only 3 options(Admin or Customer or Employee)")
    private String role;
}
