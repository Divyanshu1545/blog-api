package com.divyanshu.bloggingapi.services;

import com.divyanshu.bloggingapi.entities.User;
import com.divyanshu.bloggingapi.exceptions.ResourceNotFoundException;
import com.divyanshu.bloggingapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userRepository.findByEmail(username).orElseThrow(()-> new RuntimeException("User Not found"));

    }
}
