package com.example.first;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import jakarta.persistence.Id;

@Document(collection = "logins")
public class User {
    @Id
    private String id;

    private String username;

    private String Password;

    private String Email;

    private String Roles;

    private List<GrantedAuthority> authorities;

    public User() {

    }

    public User(User user) {
        this.username = user.username;
        this.Password = user.Password;
        this.Email = user.Email;
        this.authorities = Arrays.stream(user.getRoles().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return Password;
    }

    public String getId() {
        return id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public void setPassword(String password) {
        this.Password = password;
    }

    public String getRoles() {
        return Roles;
    }

    public void setRoles(String roles) {
        Roles = roles;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", Username=" + username + ", Password=" + Password + ", Email=" + Email + "]";
    }

}
