package com.tinqin.academy.core.processors.book;

import static com.tinqin.academy.api.ValidationMessages.AUTHOR_NOT_FOUND;

import com.tinqin.academy.api.book.create.CreateBook;
import com.tinqin.academy.api.book.create.CreateBookInput;
import com.tinqin.academy.api.book.create.CreateBookOutput;
import com.tinqin.academy.api.errors.OperationError;
import com.tinqin.academy.core.errorhandler.base.ErrorHandler;
import com.tinqin.academy.core.exceptions.BusinessException;
import com.tinqin.academy.persistence.models.Author;
import com.tinqin.academy.persistence.models.Book;
import com.tinqin.academy.persistence.repositories.AuthorRepository;
import com.tinqin.academy.persistence.repositories.BookRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
//import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateBookOperation implements CreateBook {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final ErrorHandler errorHandler;

    @Override
    public Either<OperationError, CreateBookOutput> process(CreateBookInput input) {
        return getAuthor(input)
                .flatMap(author -> createBook(input, author))
                .flatMap(this::saveBook)
                .toEither()
                .mapLeft(errorHandler::handle);
    }

    private Try<Author> getAuthor(CreateBookInput input) {
        return Try.of(() -> UUID.fromString(input.getAuthor()))
                .flatMap(authorId -> Try.of(() -> authorRepository.findById(authorId)
                        .orElseThrow(() -> new BusinessException(AUTHOR_NOT_FOUND))));
    }

    private Try<Book> createBook(CreateBookInput input, Author author) {
        return Try.of(() -> Book.builder()
                .title(input.getTitle())
                .author(author)
                .pages(input.getPages())
                .price(BigDecimal.valueOf(Double.parseDouble(input.getPrice())))
                .build());
    }

    private Try<CreateBookOutput> saveBook(Book book) {
        return Try.of(() -> bookRepository.save(book))
                .map(savedBook -> CreateBookOutput.builder()
                        .bookId(savedBook.getId())
                        .build());
    }
}
