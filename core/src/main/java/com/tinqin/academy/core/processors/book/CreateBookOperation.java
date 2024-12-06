package com.tinqin.academy.core.processors.book;

import com.tinqin.academy.api.book.create.CreateBook;
import com.tinqin.academy.api.book.create.CreateBookInput;
import com.tinqin.academy.api.book.create.CreateBookOutput;
import com.tinqin.academy.api.errors.OperationError;
import com.tinqin.academy.core.errorhandler.base.ErrorHandler;
import com.tinqin.academy.core.exceptions.BusinessException;
import com.tinqin.academy.persistence.models.Author;
import com.tinqin.academy.persistence.models.Book;
import com.tinqin.academy.persistence.models.User;
import com.tinqin.academy.persistence.repositories.AuthorRepository;
import com.tinqin.academy.persistence.repositories.BookRepository;
import com.tinqin.academy.persistence.repositories.UserRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
//import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static com.tinqin.academy.api.ValidationMessages.*;

@Service
@RequiredArgsConstructor
public class CreateBookOperation implements CreateBook {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final ErrorHandler errorHandler;
    private final UserRepository userRepository;

    @Override
    public Either<OperationError, CreateBookOutput> process(CreateBookInput input) {

        return Try.of(() -> {
                            validateAdmin(UUID.fromString(input.getUserId()));
                            return getAuthor(input)
                                    .flatMap(author -> createBook(input, author))
                                    .flatMap(this::saveBook).get();
                        }
                ).toEither()
                .mapLeft(errorHandler::handle);
    }

    private Try<Author> getAuthor(CreateBookInput input) {
        return Try.of(() -> UUID.fromString(input.getAuthor()))
                .flatMap(authorId -> Try.of(() -> authorRepository.findById(authorId)
                        .orElseThrow(() -> new BusinessException(AUTHOR_NOT_FOUND))));
    }

    private Try<?> getAdmin(CreateBookInput input) {
        return Try.of(() -> UUID.fromString(input.getUserId()))
                .flatMap(userId -> Try.of(() -> userRepository.findById(userId)
                        .filter(u -> u.isAdmin())
                        .orElseThrow(() -> new BusinessException(ADMIN_NOT_FOUND))));
    }

    private void validateAdmin(UUID adminId) {
        Optional<User> user = userRepository.findById(adminId);
        if (!user.isPresent()) {
            throw new BusinessException(USER_IS_NOT_FOUND);
        }
        if (!user.get().isAdmin()) {
            throw new BusinessException(USER_IS_NOT_ADMIN);
        }
    }

    private Try<Book> createBook(CreateBookInput input, Author author) {
        return Try.of(() -> Book.builder()
                .title(input.getTitle())
                .author(author)
                .pages(input.getPages())
                .price(BigDecimal.valueOf(Double.parseDouble(input.getPrice())))
                .pricePerRental(BigDecimal.valueOf(Double.parseDouble(input.getPricePerRental())))
                .stock(0) // Book(String title, Author author, String pages, BigDecimal price, Integer stock)
                .build());
    }

    private Try<CreateBookOutput> saveBook(Book book) {
        return Try.of(() -> bookRepository.save(book))
                .map(savedBook -> CreateBookOutput.builder()
                        .bookId(savedBook.getId())
                        .build());
    }
}
