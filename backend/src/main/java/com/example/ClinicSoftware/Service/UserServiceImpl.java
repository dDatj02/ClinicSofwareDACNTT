package com.example.ClinicSoftware.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ClinicSoftware.Entity.Role;
import com.example.ClinicSoftware.Entity.User;
import com.example.ClinicSoftware.Repository.RoleRepository;
import com.example.ClinicSoftware.Repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j

public class UserServiceImpl implements UserService
{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;
    
    @Override
    public User saveUser(User user) 
    {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        //Check if user already exists
        if(userRepository.findByEmail(user.getEmail()).isPresent())
        {
            return null;
        }
        return userRepository.save(user);
    }

    @Override
    public Role saveRole(Role role)
    {
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String rolename) 
    {
        User user = userRepository.findByEmail(username).get();
        Role role = roleRepository.findByName(rolename);
        user.getRoles().add(role);  
    }

    @Override
    public List<User> getUsersDoctor() 
    {
        return userRepository.findByRoleName("ROLE_MANAGER");
    }

    @Override
    public User getUser(String id) 
    {
        return userRepository.findById(Long.parseLong(id)).orElseThrow(() -> new RuntimeException("User not found"));
    }
    
    
}
