package com.wo.reservationservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/")
@CrossOrigin
public class HomeController {

    @GetMapping
    public ResponseEntity<Void> home() {
        return ResponseEntity.status(302).location(URI.create("http://localhost:8083/swagger-ui/index.html")).build();
    }

}
