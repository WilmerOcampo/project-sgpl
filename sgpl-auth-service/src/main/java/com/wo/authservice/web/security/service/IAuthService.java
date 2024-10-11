package com.wo.authservice.web.security.service;

import com.wo.authservice.web.security.payload.LoginRequest;
import com.wo.authservice.web.security.payload.LoginResponse;

public interface IAuthService {

    LoginResponse login(LoginRequest userRequest);

}
