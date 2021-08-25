package com.example.sprintrestanimemvc.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class UserLogin {

    @NotBlank(message = "{users.username} {error.notblank}")
    private String username;

    @NotBlank(message = "{users.password} {error.notblank}")
    private String password;
}
