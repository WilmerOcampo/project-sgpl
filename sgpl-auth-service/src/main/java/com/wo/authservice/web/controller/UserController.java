package com.wo.authservice.web.controller;

import com.wo.authservice.domain.dto.details.UserDetailsDto;
import com.wo.authservice.domain.response.OperationResponse;
import com.wo.authservice.domain.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@CrossOrigin
public class UserController {

    private final IUserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<UserDetailsDto>> findAll() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDetailsDto> findById(@PathVariable("id") Long id) {
        return userService.findById(id)
                .map(u -> new ResponseEntity<>(u, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<OperationResponse> deleteById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userService.deleteById(id), HttpStatus.OK);
    }
}
