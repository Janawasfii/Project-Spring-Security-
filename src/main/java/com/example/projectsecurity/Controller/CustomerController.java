package com.example.projectsecurity.Controller;

import com.example.projectsecurity.DTO.CustomerDTO;

import com.example.projectsecurity.Model.User;
import com.example.projectsecurity.Service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/get")
    public ResponseEntity getAllCustomer(){
        return ResponseEntity.status(200).body(customerService.getAllCustomer());
    }

    @PostMapping("/register")
    public ResponseEntity registerCustomer( @Valid @RequestBody CustomerDTO customerDTO){
        customerService.registerCustomer(customerDTO);
        return ResponseEntity.status(200).body("Customer registered successfully");}


    @PutMapping("/update")
    public ResponseEntity updateCustomer(@AuthenticationPrincipal User user,@Valid @RequestBody CustomerDTO customerDTO){
        customerService.updateCustomer(customerDTO,user.getId());
        return ResponseEntity.status(200).body("Customer updated successfully");}


    @DeleteMapping("/delete")
    public ResponseEntity deleteCustomer(@AuthenticationPrincipal User user){
        customerService.deleteCustomer(user.getId());
        return ResponseEntity.status(200).body("Customer deleted successfully");
    }






}