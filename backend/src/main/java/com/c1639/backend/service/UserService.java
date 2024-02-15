package com.c1639.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    public String welcome() {
        return "Welcome to the User Service";
    }
}
