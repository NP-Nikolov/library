package com.tinqin.academy.persistence.seeders;

import com.tinqin.academy.persistence.filereaderfactory.FileReaderFactory;
import com.tinqin.academy.persistence.filereaderfactory.models.BookModel;
import com.tinqin.academy.persistence.filereaderfactory.base.FileReader;
//import com.tinqin.academy.persistence.filereaderfactory.FileReader;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;

//@Component
@RequiredArgsConstructor
//@Order(4)
public class BookSeederFileReader implements ApplicationRunner {

    @Value("${spring.datasource.url}")
    private String jdbcUrl;

    @Value("${spring.datasource.username}")
    private String postgresUsername;

    @Value("${spring.datasource.password}")
    private String postgresPassword;

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
                        AND last_name = ?))
            """;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Connection connection = DriverManager.getConnection(jdbcUrl, postgresUsername, postgresPassword);

        PreparedStatement ps = connection.prepareStatement(BOOKS_QUERY);

        //FileReader fileReader = FileReader.loadFile("books.csv", 2); - vij comentiraniq import
        //FileReader fileReader = FileReaderFactory.createCsvFileReader("books.csv", 2);
        FileReader fileReader = FileReaderFactory.createJsonFileReader("books.json", 2);

        List<BookModel> batch = fileReader.getBatch();

        while (!batch.isEmpty()) {
            for (BookModel book : batch) {
                ps.setString(1, book.getPages());
                ps.setBigDecimal(2, book.getPrice());
                ps.setBigDecimal(3, book.getPricePerRental());
                ps.setString(4, book.getTitle());
                ps.setString(5, book.getAuthorFirstName());
                ps.setString(6, book.getAuthorLastName());

                ps.addBatch();
                ps.clearParameters();
            }

            ps.executeBatch();
            batch = fileReader.getBatch();
        }
    }
}