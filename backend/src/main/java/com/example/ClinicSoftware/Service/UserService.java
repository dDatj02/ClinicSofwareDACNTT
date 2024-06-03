package com.example.ClinicSoftware.Service;

import java.util.List;

import com.example.ClinicSoftware.Entity.Role;
import com.example.ClinicSoftware.Entity.User;

public interface UserService 
{
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String rolename);
    List<User> getUsersDoctor();
    User getUser(String id);
}
