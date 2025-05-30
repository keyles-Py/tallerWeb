package com.app.citas.security.service;

import com.app.citas.dto.UserDTO;
import com.app.citas.dto.UserRequest;
import com.app.citas.model.Role;
import com.app.citas.model.User;
import com.app.citas.repositories.RoleRepository;
import com.app.citas.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserInfoService implements UserDetailsService {
    @Autowired
    private RoleRepository rolUsuarioRepository;
    private final UserRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UserInfoService(UserRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> usuarioDetail = usuarioRepository.findByEmail(email);
        return usuarioDetail.map(UserInfoDetail::new).orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con correo: " + email));
    }

    public void addUsuario(UserDTO dto) {
        Role rol = rolUsuarioRepository.findByName(dto.getRole().getName())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado: " + dto.getRole().getName()));

        User usuario = new User();
        usuario.setFirstName(dto.getFirstName());
        usuario.setLastName(dto.getLastName());
        usuario.setEmail(dto.getEmail());
        usuario.setPassword(passwordEncoder.encode(dto.getPassword()));
        usuario.addRole(rol);


        User usuarioGuardado  = usuarioRepository.save(usuario);

    }
}