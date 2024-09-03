package com.example.projectsecurity.Service;

import com.example.projectsecurity.API.APIException;
import com.example.projectsecurity.Model.User;
import com.example.projectsecurity.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;

    public List<User> getAllUser() {
        return authRepository.findAll();}



    public void deleteUser(Integer id) {
        User u = authRepository.findUserById(id);
        if(u == null){
            throw new APIException("User not found");}
        authRepository.delete(u);

    }
}
