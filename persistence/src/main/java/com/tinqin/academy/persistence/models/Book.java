package com.tinqin.academy.persistence.models;

import com.tinqin.academy.persistence.enums.BookStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "author",nullable = false)
    private UUID author;

    @Column(name = "pages", nullable = false)
    private String pages;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "stock",nullable = false)
    private Integer stock;

    @Column(name = "createdAd",nullable = false)
    private LocalDateTime createdAd;

    @Column(name = "isDeleted")
    private Boolean isDeleted;

    @Column(name = "book_status",nullable = false)
    @Enumerated(EnumType.STRING)
    private BookStatus bookStatus;

    /*
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    List<BookRental> rentals;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private User autor;

    @ManyToMany
    @JoinTable(
            name = "category",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;

     */



}
