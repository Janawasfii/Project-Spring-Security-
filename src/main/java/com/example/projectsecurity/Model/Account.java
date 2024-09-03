package com.example.projectsecurity.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @NotEmpty(message = "Account Number cannot be empty")
    @Pattern(regexp = "^[0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{4}$")
    //@Column(columnDefinition = "varchar(16) not null unique")
    private String accountNumber;

    @NotNull(message = "Balance cannot be null")
    @Positive(message = "Balance should be positive number")
   // @Column(columnDefinition = "DOUBLE not null")
    private double balance;

    @NotEmpty(message = "Is Active cannot be empty")
    @AssertFalse
  //  @Column(columnDefinition ="BOOLEAN Default false")
    private Boolean isActive;

    @ManyToOne
    @JsonIgnore
    //@JoinColumn(name="customer_id", referencedColumnName = "id")
    private Customer customer;




}
