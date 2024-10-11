package com.wo.authservice.web.controller;

import com.wo.authservice.domain.dto.details.RoleDetailsDto;
import com.wo.authservice.domain.request.RegisterRoleRequest;
import com.wo.authservice.domain.response.ActiveRoleResponse;
import com.wo.authservice.domain.response.OperationResponse;
import com.wo.authservice.domain.service.IRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/role")
@RequiredArgsConstructor
@CrossOrigin
public class RoleController {

    private final IRoleService roleService;

    @GetMapping("/all")
    public ResponseEntity<List<RoleDetailsDto>> findAll() {
        return new ResponseEntity<>(roleService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/true")
    public ResponseEntity<List<ActiveRoleResponse>> findAllByActiveTrue() {
        return new ResponseEntity<>(roleService.findAllByActiveTrue(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<RoleDetailsDto> findById(@PathVariable("id") Long id) {
        return roleService.findById(id)
                .map(r -> new ResponseEntity<>(r, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    public ResponseEntity<OperationResponse> save(@RequestBody RegisterRoleRequest roleRequest) {
        if (roleRequest.id() == null) {
            return new ResponseEntity<>(roleService.save(roleRequest), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(roleService.save(roleRequest), HttpStatus.OK);
        }
    }

}
