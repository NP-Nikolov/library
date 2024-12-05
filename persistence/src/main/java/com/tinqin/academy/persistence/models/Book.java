package com.tinqin.academy.persistence.models;

import com.tinqin.academy.persistence.enums.BookStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name = "books")
public class Book {

    @Builder
    public Book(String title, Author author, String pages, BigDecimal price, BigDecimal pricePerRental, Integer stock) {
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.price = price;
        this.pricePerRental = pricePerRental;
        this.stock = stock;
        this.isDeleted = false;
        this.createdAd = LocalDateTime.now();
        this.bookStatus = BookStatus.PUBLISHED;
        //this.id = UUID.randomUUID();

    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "title", nullable = false)
    private String title;

    @ManyToOne(fetch = FetchType.EAGER)
    private Author author;

    @Column(name = "pages", nullable = false)
    private String pages;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "price_per_rental", nullable = false)
    private BigDecimal pricePerRental;

    @Column(name = "stock", nullable = false)
    private Integer stock;

    @CreationTimestamp
    @Column(name = "created_ad", nullable = false)
    private LocalDateTime createdAd;

    @UpdateTimestamp
    @Column(name = "updated_on")
    private LocalDateTime updatedAd;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "book_status", nullable = false)
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
