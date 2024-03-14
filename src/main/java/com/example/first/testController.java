package com.example.first;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class testController {

    @Autowired
    testService testService;

    @Autowired
    JwtService jwtService;

    List<User> user = new ArrayList<>();

    @PostMapping("/SignUser")
    public ResponseEntity<?> addUser(@RequestBody User newuser) {
        User UpdatedUser = testService.newUser(newuser);
        String Token = jwtService.generateToken(UpdatedUser.getUsername());
        return new ResponseEntity<>(Token, HttpStatus.OK);

    }

    @GetMapping("/users")
    public String hello() {
        return "ResponseEntity";
    }

    @PostMapping("/isUser")
    public ResponseEntity<?> GetUserByUsername(@RequestBody User newUser) {
        // try {
        //     if (testService.GetUserByUsername(newUser)) {
        //         String Token = jwtService.generateToken(newUser.getUsername());
        //         return new ResponseEntity<>(Token, HttpStatus.OK);
        //     } else {
        //         return new ResponseEntity<>("Incorrect Password", HttpStatus.ACCEPTED);
        //     }
        // } catch (Exception E) {
            return new ResponseEntity<>("User Not Found", HttpStatus.ACCEPTED);
        // }

    }

    @GetMapping("/users/{id}")
    public List<User> getTheUser(@PathVariable String id) {
        try {
            return testService.getTheUser(id);
        } catch (Exception E) {
            return user;
        }
    }

    @PutMapping("/update/{_id}")
    public String GetTheId(@PathVariable String _id, @RequestBody(required = false) String Username) {
        try {
            return testService.UpdateUser(_id, Username);
        } catch (Exception E) {
            return E.toString();
        }
    }

    @DeleteMapping("/users/{id}")
    public String DeleteUser(@PathVariable String id) {
        try {
            testService.DeleteUser(id);
        } catch (Exception E) {
            return E.toString();
        }
        return "Success";
    }

    @PostMapping("/generateToken")
    public String authenticateAndGetToken(@RequestBody User user) {
        try {
            if (testService.GetUserByUsername(user)) {
                return jwtService.generateToken(user.getUsername());
            } else {
                return "InValid Cretencial";
            }
        } catch (Exception E) {
            return E.toString();
        }

    }

    @PostMapping("/validateToken")
    public ResponseEntity<?> ValidateToken(@RequestBody String token) {
        String username = null;
        username = jwtService.extractUsername(token);
        if (username != null) {
            User res = testService.findbyName(username);
            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        return new ResponseEntity<>("Invalid or Expired", HttpStatus.ACCEPTED);
    }
}
