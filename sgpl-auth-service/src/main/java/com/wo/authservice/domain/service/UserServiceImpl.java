package com.wo.authservice.domain.service;

import com.wo.authservice.domain.dto.details.UserDetailsDto;
import com.wo.authservice.domain.dto.register.RegisterUserDto;
import com.wo.authservice.domain.repository.IUserDtoRepository;
import com.wo.authservice.domain.response.OperationResponse;
import com.wo.authservice.util.UserUtil;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final IUserDtoRepository userDtoRepository;

    @Override
    public List<UserDetailsDto> findAll() {
        return userDtoRepository.findAll();
    }

    @Override
    public Optional<UserDetailsDto> findById(Long id) {
        return userDtoRepository.findById(id);
    }

    @Override
    public OperationResponse save(RegisterUserDto userDto) {
        try {
            if (userDto.getRoleIds().isEmpty() || userDto.getRoleIds().contains(0L)) {
                userDto.setRoleIds(Set.of(2L));
            }
            userDtoRepository.save(userDto);
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
            userDtoRepository.deleteById(id);
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
}
