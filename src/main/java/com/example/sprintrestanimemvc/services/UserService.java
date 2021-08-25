package com.example.sprintrestanimemvc.services;

import com.example.sprintrestanimemvc.daos.ApiDAO;
import com.example.sprintrestanimemvc.exceptions.UserAlreadyExistsException;
import com.example.sprintrestanimemvc.models.User;
import com.example.sprintrestanimemvc.models.UserRegistration;
import com.example.sprintrestanimemvc.responses.LoginResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;


@Service
public class UserService {

    private final ApiDAO apiDAO;

    public UserService(ApiDAO apiDAO) {
        this.apiDAO = apiDAO;
    }

    public User register(UserRegistration userRegistration) {
        try {
            return apiDAO.register(userRegistration);
        } catch (HttpClientErrorException e) {
                throw new UserAlreadyExistsException(userRegistration.getUsername());
            }
    }

    public LoginResponse login(String username, String password) {
        return apiDAO.login(username, password);
    }

}
