package com.minkyu.realworld.user.application;

import com.minkyu.realworld.user.domain.repository.UserRepository;
import com.minkyu.realworld.user.presentation.UserController;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserService {

    private final UserRepository userRepository;
}