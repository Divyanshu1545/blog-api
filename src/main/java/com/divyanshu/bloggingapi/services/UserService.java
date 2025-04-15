package com.divyanshu.bloggingapi.services;

import com.divyanshu.bloggingapi.payloads.UserDto;

import java.util.List;

public interface UserService {
   UserDto createUser(UserDto userDto);
   UserDto updateUser(UserDto userDto, Integer id);
   UserDto getUserById(Integer id);
   List<UserDto> getAllUsers();
   void deleteUser(Integer id);
}
