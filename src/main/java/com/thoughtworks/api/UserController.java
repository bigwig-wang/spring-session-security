package com.thoughtworks.api;

import com.thoughtworks.dto.UserInfoDTO;
import com.thoughtworks.entity.User;
import com.thoughtworks.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public User login(@RequestBody UserInfoDTO userInfoDTO) {
        return userService.findByUsernameAndPassword(userInfoDTO);
    }

    @PostMapping("/logout")
    public void logout() {
        SecurityContextHolder.clearContext();
    }

    @GetMapping
    public Object getUser() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
