package com.divyanshu.bloggingapi.services.impl;

import com.divyanshu.bloggingapi.entities.User;
import com.divyanshu.bloggingapi.exceptions.ResourceNotFoundException;
import com.divyanshu.bloggingapi.payloads.UserDto;
import com.divyanshu.bloggingapi.repositories.UserRepository;
import com.divyanshu.bloggingapi.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public UserDto createUser(UserDto userDto) {
    User user = dtoToUser(userDto);
    User savedUser = userRepository.save(user);
    return this.userToDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer id) {
        User user =  userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User","id", id));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        User updatedUser = userRepository.save(user);
        return userToDto(updatedUser);
    }

    @Override
    public UserDto getUserById(Integer id) {
      User user =  userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User","id", id));
      return userToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(this::userToDto).collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Integer id) {
        User user =  userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User","id", id));
        userRepository.delete(user);
    }

    private User dtoToUser(UserDto userDto){
        return modelMapper.map(userDto,User.class);
    }
    private UserDto userToDto(User user){

        return modelMapper.map(user,UserDto.class);
    }
}
