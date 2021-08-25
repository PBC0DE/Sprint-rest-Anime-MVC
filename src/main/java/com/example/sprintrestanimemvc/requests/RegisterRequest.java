package com.example.sprintrestanimemvc.requests;

import com.example.sprintrestanimemvc.models.UserRegistration;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
public class RegisterRequest {

    private String username;

    @ToString.Exclude
    private String password;

    private String email;


    public RegisterRequest(UserRegistration userRegistration) {
        this.username = userRegistration.getUsername();
        this.password = userRegistration.getPassword();
    }
}
