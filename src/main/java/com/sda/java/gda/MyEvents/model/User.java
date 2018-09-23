package com.sda.java.gda.MyEvents.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Collection;

@Data
@Entity
@Table(name = "users")
public class User extends BaseEntity implements UserDetails {


    @NotBlank
    @Column(nullable = false, unique = true)
    private String login;

    @NotBlank
    @Enumerated(EnumType.STRING)
    @Column(name = "role",nullable = false)
    private UserRole role;

    @NotBlank
    @Column(nullable = false)
    private String password;

    @Email
    @Column(nullable = false, name = "email")
    private String email;

    @Column(nullable = false, name = "username")
    private String username;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public boolean isAdmin() {
        return this.role.equals(UserRole.ADMINISTRATOR);
    }
}
