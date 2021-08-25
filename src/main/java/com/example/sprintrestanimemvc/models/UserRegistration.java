package com.example.sprintrestanimemvc.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class UserRegistration {


    @NotBlank(message = "{users.username} {error.notblank}")
    private String username;

    @Size(min = 6, max = 30, message = "{users.password} {error.size}")
    private String password;

    @Size(min = 6, max = 30, message = "{users.password} {error.size}")
    private String confirmPassword;

    private Boolean isAdmin = true;
}
