package com.example.projectsecurity.Repository;

import com.example.projectsecurity.Model.Account;
import com.example.projectsecurity.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    Account findAccountById(Integer id);


    List<Account> getAllMyAccountsByCustomerId(Customer customer);
}
