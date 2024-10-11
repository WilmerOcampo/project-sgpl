package com.wo.authservice.web.security;

import com.wo.authservice.domain.dto.register.RegisterUserDto;
import com.wo.authservice.domain.response.OperationResponse;
import com.wo.authservice.domain.service.IUserService;
import com.wo.authservice.web.security.payload.LoginRequest;
import com.wo.authservice.web.security.payload.LoginResponse;
import com.wo.authservice.web.security.service.IAuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthController {
    private final IUserService userService;
    private final IAuthService authService;

    @PostMapping("/user/save")
    public ResponseEntity<OperationResponse> saveUser(@RequestBody RegisterUserDto userDto) {
        if (userDto.getId() == null) {
            return new ResponseEntity<>(userService.save(userDto), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(userService.save(userDto), HttpStatus.OK);
        }
    }

    @PostMapping("/log-in")
    public ResponseEntity<LoginResponse> logIn(@RequestBody @Valid LoginRequest userRequest) {
        return new ResponseEntity<>(this.authService.login(userRequest), HttpStatus.OK);
    }

}
