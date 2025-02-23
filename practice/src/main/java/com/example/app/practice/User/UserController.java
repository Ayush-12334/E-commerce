
package com.example.app.practice.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    

    @PostMapping("/register") 
    public ResponseEntity<?> register(@RequestBody User user) {
        try {
            User registeredUser = userService.register(user);
            return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while registering the user", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/login")  
    public String login(@RequestBody User user) {  
        //TODO: process POST request
        // System.out.println(user.getEmail());
        
        return userService.verify(user);
    }
}
    
//