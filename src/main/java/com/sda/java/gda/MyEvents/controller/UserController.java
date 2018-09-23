package com.sda.java.gda.MyEvents.controller;

import com.sda.java.gda.MyEvents.exeption.NotFoundException;
import com.sda.java.gda.MyEvents.exeption.UnautorizedException;
import com.sda.java.gda.MyEvents.model.User;
import com.sda.java.gda.MyEvents.repository.UserRepository;
import com.sda.java.gda.MyEvents.security.AuthorizationHelper;
import com.sda.java.gda.MyEvents.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private AuthorizationHelper authorizationHelper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAll(){
        if(!authorizationHelper.getCurrentUser().isAdmin()){
            throw new UnautorizedException("You don't have permissuon to get other users info!");
        }
        return userRepository.findAll();
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody @Valid User user, BindingResult bindingResult){
        return userService.create(user,bindingResult);
    }
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User update(@RequestBody @Valid User user,
                       @PathVariable("id") UUID userId,
                       BindingResult bindingResult) {
        if (!hasPermissionToUser(userId)) {
            throw new UnautorizedException("You don't have permission to edit this user!");
        }

        user.setId(userId);
        return userService.update(user, userId, bindingResult);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable("id") UUID userId) {
        if (!hasPermissionToUser(userId)) {
            throw new UnautorizedException("You don't have permission to delete this user!");
        }

        if (!userRepository.existsById(userId)) {
            throw new NotFoundException(String.format("User with id: %s not found", userId));
        }

        userRepository.deleteById(userId);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.OK)
    public String logout(OAuth2Authentication auth) {
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) auth.getDetails();
        String token = details.getTokenValue();
        tokenStore.removeAccessToken(new DefaultOAuth2AccessToken(token));
        return "User logged out successfully";
    }

    private boolean hasPermissionToUser(UUID userId) {
        User currentUser = authorizationHelper.getCurrentUser();
        return currentUser.isAdmin() || userId.equals(currentUser.getId());
    }
}
