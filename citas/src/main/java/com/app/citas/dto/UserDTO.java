package com.app.citas.dto;

import com.app.citas.model.Role;
import com.app.citas.enums.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;
@Data
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private Role role;
}
