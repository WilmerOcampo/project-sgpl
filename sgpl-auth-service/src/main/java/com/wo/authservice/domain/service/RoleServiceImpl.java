package com.wo.authservice.domain.service;

import com.wo.authservice.domain.dto.details.RoleDetailsDto;
import com.wo.authservice.domain.repository.IRoleDtoRepository;
import com.wo.authservice.domain.request.RegisterRoleRequest;
import com.wo.authservice.domain.response.ActiveRoleResponse;
import com.wo.authservice.domain.response.OperationResponse;
import com.wo.authservice.util.UserUtil;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements IRoleService {

    private final IRoleDtoRepository roleDtoRepository;

    @Override
    public List<RoleDetailsDto> findAll() {
        return roleDtoRepository.findAll();
    }

    @Override
    public Optional<RoleDetailsDto> findById(Long id) {
        return roleDtoRepository.findById(id);
    }

    @Override
    public OperationResponse save(RegisterRoleRequest roleRequest) {
        try {
            roleDtoRepository.save(roleRequest);
            return new OperationResponse(UserUtil.REGISTRATION + UserUtil.SUCCESSFUL);
        } catch (ConstraintViolationException ex) {
            return new OperationResponse(handleConstraintViolationException(ex));
        } catch (Exception e) {
            return new OperationResponse(e.getLocalizedMessage());
        }
    }

    @Override
    public OperationResponse deleteById(Long id) {
        try {
            roleDtoRepository.deleteById(id);
            return new OperationResponse(UserUtil.DELETION + UserUtil.SUCCESSFUL);
        } catch (Exception e) {
            return new OperationResponse(e.getLocalizedMessage());
        }
    }

    private String handleConstraintViolationException(ConstraintViolationException ex) {
        List<String> errors = ex.getConstraintViolations().stream()
                .map(cv -> cv.getPropertyPath() + ": " + cv.getMessage())
                .toList();
        return "Validation error: " + errors;
    }

    @Override
    public List<ActiveRoleResponse> findAllByActiveTrue() {
        return roleDtoRepository.findAllByActiveTrue();
    }

}
