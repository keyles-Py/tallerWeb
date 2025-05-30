package com.app.citas.service.impl;

import com.app.citas.dto.UserDTO;
import com.app.citas.model.Role;
import com.app.citas.model.User;
import com.app.citas.repositories.RoleRepository;
import com.app.citas.repositories.UserRepository;
import com.app.citas.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class UserSeviceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository rolUsuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void register(UserDTO dto) throws Exception {
        Role rol = rolUsuarioRepository.findByName(dto.getRole().getName())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado: " + dto.getRole().getName()));

        User usuario = new User();
        usuario.setFirstName(dto.getFirstName());
        usuario.setLastName(dto.getLastName());
        usuario.setEmail(dto.getEmail());
        usuario.setUsername(dto.getUsername());
        usuario.setPassword(passwordEncoder.encode(dto.getPassword()));
        usuario.addRole(rol);


        User usuarioGuardado = userRepository.save(usuario);
    }

    @Override
    public void deleteUser(Long id) throws Exception {
        if (!userRepository.existsById(id)) throw new Exception("User not found");
        userRepository.deleteById(id);

    }
}
