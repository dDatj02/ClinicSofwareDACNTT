package com.example.ClinicSoftware.Entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Getter
@Setter
@Data
@NoArgsConstructor
@Table(name = "Users")

public class User implements UserDetails
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String full_name;
    private String user_name;
    private String email;
    private String password;
    private String specialist;
    private String moreInfo;
    
    
    @ManyToMany
    @JoinTable(name = "users_role", joinColumns = @JoinColumn(name = "Users_Id"), inverseJoinColumns = @JoinColumn(name = "Roles_ID"))
    private Set<Role> roles = new HashSet<>();

    public User(Long id, String full_name, String user_name, String email, String password, Set<Role> roles) //For normal user
    {
        this.id = id;
        this.full_name = full_name;
        this.user_name = user_name;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }


    public User(Long id, String full_name, String user_name, String email, String password, String specialist, String moreInfo, Set<Role> roles) //For doctor user
    {
        this.id = id;
        this.full_name = full_name;
        this.user_name = user_name;
        this.email = email;
        this.password = password;
        this.specialist = specialist;
        this.moreInfo = moreInfo;
        this.roles = roles;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        roles.stream().forEach(i->authorities.add(new SimpleGrantedAuthority(i.getName())));
        return List.of(new SimpleGrantedAuthority(authorities.toString()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public String getFullName() 
    {
        return full_name;
    }

    public String getSpecialist() 
    {
        return specialist;
    }

    public String getMoreInfo() 
    {
        return moreInfo;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
}
