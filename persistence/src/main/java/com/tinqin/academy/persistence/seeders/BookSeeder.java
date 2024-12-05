package com.tinqin.academy.persistence.seeders;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

@Component
@RequiredArgsConstructor
@Order(2)
public class BookSeeder implements ApplicationRunner {

    @Value("${spring.datasource.url}")
    private String jdbcUrl;

    @Value("${spring.datasource.username}")
    private String postgresUsername;

    @Value("${spring.datasource.password}")
    private String postgresPassword;

    @AllArgsConstructor
    @Builder
    @Getter
    private static class BookTemplete {
        private final String title;
        private final String pages;
        private final BigDecimal price;
        private final BigDecimal pricePerRental;
        private final String authorFirstName;
        private final String authorLastName;
    }

    private final List<BookTemplete> books = List.of(
            BookTemplete.builder()
                    .title("Pod Igoto1a")
                    .pages("300")
                    .price(BigDecimal.valueOf(25.9))
                    .pricePerRental(BigDecimal.valueOf(2.5))
                    .authorFirstName("Ivan")
                    .authorLastName("Vazov")
                    .build(),
            BookTemplete.builder()
                    .title("Elegiya1a")
                    .pages("200")
                    .price(BigDecimal.valueOf(22.22))
                    .pricePerRental(BigDecimal.valueOf(2))
                    .authorFirstName("Hristo")
                    .authorLastName("Botev")
                    .build()
    );

    String BOOKS_QUERY = """
            INSERT INTO books (id, created_ad, is_deleted, pages, price, price_per_rental, stock, title, book_status, author_id)
            VALUES (gen_random_uuid(),
                    now(),
                    false,
                    ?,
                    ?,
                    ?,
                    0,
                    ?,
                    'UNPUBLISHED',
                    (SELECT id
                     FROM authors
                     WHERE first_name = ?
                        AND last_name = ?
                        AND id = '0790db67-24dd-4ba9-8768-78bc143a76d6'))
            """;
    // - vtorata liniq se zaredi s avtor null - zaradi idto

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Connection connection = DriverManager.getConnection(jdbcUrl, postgresUsername, postgresPassword);

        /*
        ResultSet resultSet = connection
                .prepareStatement("SELECT COUNT(*) FROM authors")
                .executeQuery();

        resultSet.next();
        Integer bookCount = resultSet.getInt(1);

        if (bookCount > 0) {
            return;
        }
         */

        PreparedStatement ps = connection.prepareStatement(BOOKS_QUERY);

        for (BookTemplete book : books) {
            ps.setString(1, book.getPages());
            ps.setBigDecimal(2, book.getPrice());
            ps.setBigDecimal(3, book.getPricePerRental());
            ps.setString(4, book.getTitle());
            ps.setString(5, book.getAuthorFirstName());
            ps.setString(6, book.getAuthorLastName());

            ps.addBatch();
            ps.clearParameters();
        }

        //ps.executeBatch();    //za da ne updeitvam
    }
}
