package com.minkyu.realworld.user.presentation;

import com.minkyu.realworld.user.domain.repository.UserRepostory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserRepostory userRepostory;
}