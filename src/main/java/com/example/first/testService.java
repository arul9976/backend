package com.example.first;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class testService implements UserDetailsService {

    @Autowired
    UserRepository userRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    public List<User> getUser() {
        return userRepo.findAll();
    }

    public Boolean GetUserByUsername(User user) throws Exception {

        if (!userRepo.findByUsername(user.getUsername()).isEmpty()) {
            if (passwordEncoder.matches(user.getPassword(),
                    userRepo.findByUsername(user.getUsername()).getFirst().getPassword())) {
                return true;
            } else {
                return false;
            }
        } else {
            throw new Exception("Not Found");
        }

    }

    public User newUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    public void DeleteUser(String _id) {
        if (!isExists(_id)) {
            throw new IllegalStateException("Students id " + _id + " not exists");
        }
        userRepo.deleteById(_id);
    }

    public String UpdateUser(String _id, String Username) {
        if (!isExists(_id)) {
            return "Students id " + _id + " not exists";
        }
        Optional<User> user = userRepo.findById(_id);
        if (user.isPresent()) {
            User _user = user.get();
            _user.setUsername(Username);
            _user.setEmail(Username + "@gmail.com");
            userRepo.save(_user);
            return "namess " + _user.getUsername() + " " + _user.getEmail();
        }

        return "name " + Username;

    }

    private Boolean isExists(String _id) {
        boolean exists = userRepo.existsById(_id);
        return exists;
    }

    List<User> user = new ArrayList<>();

    public List<User> getTheUser(String id) {
        if (!isExists(id)) {
            throw new IllegalStateException("Not Found " + id);
        }
        user.clear();
        user.add(userRepo.findById(id).get());
        return user;

    }

    public User findbyName(String username){
        return userRepo.findByUsername(username).getFirst();
        
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'loadUserByUsername'");
    }

    
}
