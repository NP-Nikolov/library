package com.tinqin.academy.repositories;

import com.tinqin.academy.models.Book;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, UUID> {

}
