package com.example.demo.config.auth;

import org.springframework.security.access.AccessDeniedException;

public class MyaccessDeniedException extends AccessDeniedException {


    public MyaccessDeniedException(String msg) {
        super(msg);
    }
}
