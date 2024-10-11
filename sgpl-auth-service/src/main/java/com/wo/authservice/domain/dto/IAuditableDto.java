package com.wo.authservice.domain.dto;

public interface IAuditableDto {

    void setCreatedBy(String createdBy);

    void setUpdatedBy(String updatedBy);

}
