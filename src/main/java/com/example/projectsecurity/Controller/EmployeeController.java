package com.example.projectsecurity.Controller;

import com.example.projectsecurity.DTO.EmployeeDTO;
import com.example.projectsecurity.Model.User;
import com.example.projectsecurity.Service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employee")
@RequiredArgsConstructor

public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping("/get")
    public ResponseEntity getAllEmployee(){
        return ResponseEntity.status(200).body(employeeService.getAllEmployee());
    }

    @PostMapping("/register")
    public ResponseEntity registerEmployee(@Valid @RequestBody EmployeeDTO employeeDTO){
        employeeService.registerEmployee(employeeDTO);
        return ResponseEntity.status(200).body("Employee registered successfully");}


    @PutMapping("/update")
    public ResponseEntity updateEmployee(@AuthenticationPrincipal User user, @Valid @RequestBody EmployeeDTO employeeDTO){
        employeeService.updateEmployee(employeeDTO,user.getId());
        return ResponseEntity.status(200).body("Employee updated successfully");}


    @DeleteMapping("/delete")
    public ResponseEntity deleteEmployee(@AuthenticationPrincipal User user){
        employeeService.deleteEmployee(user.getId());
        return ResponseEntity.status(200).body("Employee deleted successfully");}

}