package com.example.sprintrestanimemvc.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    private String username;

    @ToString.Exclude
    private String password;
}
