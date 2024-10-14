package com.wo.bookservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "books")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Book extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(name = "title", length = 200, nullable = false)
    private String title;

    @Column(length = 13, nullable = false)
    private String isbn;

    @Column(name = "author", length = 200, nullable = false)
    private String author;

    @Column(name = "num_pages", nullable = false)
    private int numPages;

    @Column(name = "cover_image")
    private String coverImage;

    @Column(name = "quantity_available", nullable = false)
    private int quantityAvailable;

    @Column(name = "publication_year", nullable = false)
    private int publicationYear;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "category_id")
    private Long categoryId;

}
