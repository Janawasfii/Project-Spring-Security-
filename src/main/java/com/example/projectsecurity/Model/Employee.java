package com.example.projectsecurity.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Position cannot be empty")
    //@Column(columnDefinition = "varchar(30) not null")
    private String position;

    @NotNull(message = "Salary cannot be null")
  //  @Positive(message = "Salary should be positive number")
    //@Column(columnDefinition = "DOUBLE not null")
    private double salary;



    @OneToOne
    @MapsId
    @JsonIgnore
    private User user;


}
