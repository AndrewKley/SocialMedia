package com.SocialMedia.app.services;

import com.SocialMedia.app.models.Role;
import com.SocialMedia.app.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public Optional<Role> findByRole(String role) {
        return roleRepository.findByRole(role);
    }

    public void save(Role role) {
        if (!roleRepository.findByRole(role.getRole()).isPresent()) {
            roleRepository.save(role);
        }
    }
}
