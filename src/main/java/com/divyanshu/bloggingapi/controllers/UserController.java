package com.divyanshu.bloggingapi.controllers;

import com.divyanshu.bloggingapi.entities.User;
import com.divyanshu.bloggingapi.payloads.ApiResponse;
import com.divyanshu.bloggingapi.payloads.UserDto;
import com.divyanshu.bloggingapi.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping(path = "/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        UserDto createdUser = userService.createUser(userDto);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<UserDto> updateUser(@Valid @PathVariable("id") Integer id,@RequestBody UserDto userDto ){
        UserDto updatedUser = userService.updateUser(userDto, id);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @GetMapping(path = "/")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> users = userService.getAllUsers();
        return new ResponseEntity<>(users,HttpStatus.OK);
    }

    @GetMapping(path = "current-user/")
    public ResponseEntity<String> getLoggedInUser(Principal principal){
        return new ResponseEntity<String>(principal.getName(), HttpStatus.OK);
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Integer id){
        UserDto user = userService.getUserById(id);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ApiResponse> deleteUserById(@PathVariable("id") Integer id){
        userService.deleteUser(id);
        return new ResponseEntity<>(new ApiResponse("User deleted successfully", true), HttpStatus.NO_CONTENT);
    }

}
