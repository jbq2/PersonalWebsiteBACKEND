package com.personalwebsite.personalwebsite.admin.user.controller;

import com.personalwebsite.personalwebsite.admin.user.pojo.User;
import com.personalwebsite.personalwebsite.admin.user.service.UserService;
import com.personalwebsite.personalwebsite.pojo.CustomResponse;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/secure/admin/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/list")
    public ResponseEntity<CustomResponse> listUsers(){
        return null;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<CustomResponse> getUser(@PathVariable("id") Long id){
        return null;
    }

    @GetMapping("/get/{username}")
    public ResponseEntity<CustomResponse> getUser(@PathVariable("username") String username){
        return null;
    }

    @PostMapping("/save")
    public ResponseEntity<CustomResponse> saveUser(@RequestBody User user){
        return null;
    }
}
