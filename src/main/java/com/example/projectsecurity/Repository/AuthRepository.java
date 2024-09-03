package com.example.projectsecurity.Repository;

import com.example.projectsecurity.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<User, Integer> {

    User findUserById(Integer id);


    User findUserByUsername(String username);

    User findUserByIdAndCustomerId(Integer id,Integer customer_id);


}
