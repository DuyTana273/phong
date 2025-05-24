package com.phong.demo.service.impl;

import com.phong.demo.repository.IUserRepository;
import com.phong.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;
}
