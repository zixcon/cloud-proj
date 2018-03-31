package com.cloud.frame.demo.auth.user.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by wd on 2018/3/30.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/principal")
    public Principal user(Principal user) {
        return user;
    }
}
