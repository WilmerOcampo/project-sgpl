package com.wo.authservice.persistence.model;

public interface IAuditableEntity {

    Long getCreatedBy();

    Long getUpdatedBy();

}
