package com.cloud.frame.demo.auth.user.service;

import com.cloud.frame.demo.auth.user.entity.RcUserEntity;
import org.springframework.stereotype.Service;

/**
 * Created by wd on 2018/3/30.
 */
@Service
public class UserService {

    public RcUserEntity findByUsername(String username) {
        return new RcUserEntity();
    }
}
