package com.tinqin.academy.persistence.repositories;

import com.tinqin.academy.persistence.models.Book;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {

}
