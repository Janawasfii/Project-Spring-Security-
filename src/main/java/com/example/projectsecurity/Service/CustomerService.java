package com.example.projectsecurity.Service;

import com.example.projectsecurity.API.APIException;
import com.example.projectsecurity.DTO.CustomerDTO;
import com.example.projectsecurity.Model.Customer;
import com.example.projectsecurity.Model.User;
import com.example.projectsecurity.Repository.AuthRepository;
import com.example.projectsecurity.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final AuthRepository authRepository;

    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    public void registerCustomer(CustomerDTO customerDTO) {
        User u = new User();
        Customer c = new Customer();
        c.setPhoneNumber(customerDTO.getPhoneNumber());
        u.setName(customerDTO.getName());
        u.setUsername(customerDTO.getUsername());
        u.setEmail(customerDTO.getEmail());
        u.setRole("CUSTOMER");
        u.setPassword(new BCryptPasswordEncoder().encode(customerDTO.getPassword()));
        c.setUser(u);
        u.setCustomer(c);
        customerRepository.save(c);
        authRepository.save(u);}



    public void updateCustomer(CustomerDTO customerDTO,Integer customer_id) {
        User u = authRepository.findUserById(customer_id);
        if (u == null) {
            throw new APIException("Customer not found");}
        u.setName(customerDTO.getName());
        u.setUsername(customerDTO.getUsername());
        u.setEmail(customerDTO.getEmail());
        u.setPassword(new BCryptPasswordEncoder().encode(customerDTO.getPassword()));
        u.getCustomer().setPhoneNumber(customerDTO.getPhoneNumber());
        authRepository.save(u);}



    public void deleteCustomer(Integer auth_Id){
       User u = authRepository.findUserById(auth_Id);
       Customer c = customerRepository.findCustomerById(auth_Id);
        if (u.getId()!= auth_Id) {
            throw new APIException("User or Customer not found");}
        customerRepository.delete(c);
        authRepository.delete(u);}





    }











