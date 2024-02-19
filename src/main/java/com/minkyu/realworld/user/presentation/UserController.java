package com.minkyu.realworld.user.presentation;

import com.minkyu.realworld.user.application.UserService;
import com.minkyu.realworld.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
}