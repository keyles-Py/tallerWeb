package com.app.citas.dto;

import com.app.citas.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private Role role;
}
