package com.sda.java.gda.MyEvents.service;

import com.sda.java.gda.MyEvents.exeption.BindingResultException;
import com.sda.java.gda.MyEvents.exeption.NotFoundException;
import com.sda.java.gda.MyEvents.model.User;
import com.sda.java.gda.MyEvents.repository.UserRepository;
import org.flywaydb.core.internal.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User get(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException(String.format("User with id: %s not found", id)));
    }

    public User create(User user, BindingResult bindingResult) {
        validate(user, null, bindingResult);
        return save(user);
    }

    public User update(User user, UUID id, BindingResult bindingResult) {
        validate(user, get(id).getUsername(), bindingResult);
        return save(user);
    }

    private void validate(User user, String currentUsername, BindingResult bindingResult) {
        if (!user.getUsername().equals(currentUsername)
                && userRepository.existsByUsername(user.getUsername())) {
            bindingResult.addError(
                    new FieldError("user", "field",
                            String.format("User with username %s already exists", user.getUsername())));
        }
        if (bindingResult.hasErrors()) {
            throw new BindingResultException(bindingResult);
        }
    }

    private User save(User user) {
        String newPassword = user.getPassword();

        if (StringUtils.hasText(newPassword)) {
            PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
            user.setPassword(encoder.encode(newPassword));
        }
        return userRepository.save(user);
    }
}
