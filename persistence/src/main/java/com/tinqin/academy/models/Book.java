package com.tinqin.academy.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter
@Setter
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
    private String author;

    @Column(name = "book_status",nullable = false)
    @Enumerated(EnumType.STRING)
    private String bookStatus;

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
