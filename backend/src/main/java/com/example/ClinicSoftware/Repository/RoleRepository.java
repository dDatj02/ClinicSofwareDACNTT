package com.example.ClinicSoftware.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ClinicSoftware.Entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>
{
    Role findByName(String role);
}
