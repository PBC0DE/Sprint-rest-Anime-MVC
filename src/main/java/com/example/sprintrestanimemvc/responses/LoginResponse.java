package com.example.sprintrestanimemvc.responses;

import com.example.sprintrestanimemvc.models.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginResponse {

    private String accessToken;

    private User user;
}
