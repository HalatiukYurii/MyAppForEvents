package com.sda.java.gda.MyEvents.service;

import com.sda.java.gda.MyEvents.exeption.NotFoundException;
import com.sda.java.gda.MyEvents.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService  {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findOneByUsername(username).orElseThrow(() -> new NotFoundException(String.format("User with username: %s not found", username)));
    }
}
