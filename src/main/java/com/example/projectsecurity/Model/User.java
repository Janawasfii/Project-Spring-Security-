package com.example.projectsecurity.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//    @NotEmpty(message = "Username cannot be empty")
//    @Size(min=5, max=11)
   // @Column(columnDefinition = "varchar(10) not null unique")
    private String username;


//    @NotEmpty(message = "Password cannot be empty")
//    @Size(min=6)
   // @Column(columnDefinition = "varchar(25) not null")
    private String password;


//    @NotEmpty(message = "Name cannot be empty")
//    @Size(min=2, max=20)
   // @Column(columnDefinition = "varchar(30) not null")
    private String name;

//    @Email
    //@Column(columnDefinition = "varchar(30) not null unique")
    private String email;

//    @NotEmpty(message = "Role cannot be empty")
//    @Pattern(regexp="^(ADMIN|CUSTOMER|EMPLOYEE)$",message = "Only 3 options(Admin or Customer or Employee)")
    //@Column(columnDefinition = "varchar(15) check (role='ADMIN' or role='CUSTOMER' or role='EMPLOYEE')")
    private String role;


    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    @PrimaryKeyJoinColumn
    private Customer customer;


    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    @PrimaryKeyJoinColumn
    private Employee employee;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(this.role));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }






}
