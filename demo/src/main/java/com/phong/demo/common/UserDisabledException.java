package com.phong.demo.common;

import org.springframework.security.core.AuthenticationException;

public class UserDisabledException extends AuthenticationException {
    public UserDisabledException(String msg) {
        super(msg);
    }
}
