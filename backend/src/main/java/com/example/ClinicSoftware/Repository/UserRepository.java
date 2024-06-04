package com.example.ClinicSoftware.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.ClinicSoftware.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
    Optional<User> findByEmail(String email);
    Optional<User> findById(Long id);
    

    //Get list user by role name
    @Query("SELECT u FROM User u JOIN u.roles r WHERE r.name = :roleName")
    List<User> findByRoleName(@Param("roleName") String roleName);

    //Get role name by user id
    @Query("SELECT r.name FROM User u JOIN u.roles r WHERE u.id = :userId")
    String findRoleNameByUserId(@Param("userId") Long userId);
}
