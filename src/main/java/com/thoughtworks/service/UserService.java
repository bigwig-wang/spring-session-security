package com.thoughtworks.service;

import com.thoughtworks.dto.UserInfoDTO;
import com.thoughtworks.entity.User;
import com.thoughtworks.exception.UserNotFoundException;
import com.thoughtworks.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findByUsernameAndPassword(UserInfoDTO userInfoDTO) {
        User user = userRepository
                .findAllByUsernameAndPassword(userInfoDTO.getUsername(), userInfoDTO.getPassword())
                .orElseThrow(UserNotFoundException::new);
        return user;

    }
}
