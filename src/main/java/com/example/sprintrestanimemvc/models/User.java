package com.example.sprintrestanimemvc.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class User {

    private Long id;

    private String username;

    private String email;

    private Set<Role> roles;

    private Boolean isAdmin;

    private String created;

    private String updated;

}
