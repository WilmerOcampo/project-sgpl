package com.wo.bookservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "categories")
@Getter
@Setter
public class Category extends Base {

    @Column(name = "name", length = 60, nullable = false)
    private String name;

    @Column(name = "is_active")
    private boolean isActive;

}
