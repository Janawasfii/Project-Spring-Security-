package com.example.projectsecurity.Service;

import com.example.projectsecurity.API.APIException;
import com.example.projectsecurity.DTO.CustomerDTO;
import com.example.projectsecurity.DTO.EmployeeDTO;
import com.example.projectsecurity.Model.Customer;
import com.example.projectsecurity.Model.Employee;
import com.example.projectsecurity.Model.User;
import com.example.projectsecurity.Repository.AuthRepository;
import com.example.projectsecurity.Repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final AuthRepository authRepository;

    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();}


    public void registerEmployee(EmployeeDTO employeeDTO) {
        User u = new User();
        Employee e = new Employee();
        u.setName(employeeDTO.getName());
        u.setUsername(employeeDTO.getUsername());
        u.setEmail(employeeDTO.getEmail());
        u.setRole("EMPLOYEE");
        u.setPassword(new BCryptPasswordEncoder().encode(employeeDTO.getPassword()));

        e.setPosition(employeeDTO.getPosition());
        e.setSalary(employeeDTO.getSalary());
        u.setEmployee(e);
        e.setUser(u);
        employeeRepository.save(e);
        authRepository.save(u);}


    public void updateEmployee(EmployeeDTO employeeDTO, Integer employee_id) {
        User u = authRepository.findUserById(employee_id);
        if(u==null){
            throw new APIException("Employee not found");
        }
        u.setName(employeeDTO.getName());
        u.setUsername(employeeDTO.getUsername());
        u.setEmail(employeeDTO.getEmail());
        u.setPassword(new BCryptPasswordEncoder().encode(employeeDTO.getPassword()));
        u.getEmployee().setPosition(employeeDTO.getPosition());
        u.getEmployee().setSalary(employeeDTO.getSalary());
        authRepository.save(u);}


    public void deleteEmployee(Integer employee_id) {
        User u = authRepository.findUserById(employee_id);
        Employee e = employeeRepository.findEmployeeById(employee_id);
        if(u.getId()!= employee_id){
            throw new APIException("Employee not found");}
        employeeRepository.delete(e);
        authRepository.delete(u);}


}
