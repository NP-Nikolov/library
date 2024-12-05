package com.tinqin.academy.rest.controllers;

import com.tinqin.academy.api.APIRoutes;
import com.tinqin.academy.api.book.create.CreateBook;
import com.tinqin.academy.api.book.create.CreateBookInput;
import com.tinqin.academy.api.book.create.CreateBookOutput;
import com.tinqin.academy.api.book.getbook.GetBook;
import com.tinqin.academy.api.book.getbook.GetBookInput;
import com.tinqin.academy.api.book.getbook.GetBookOutput;
import com.tinqin.academy.api.book.getbooksbyauthor.GetBookByAuthor;
import com.tinqin.academy.api.book.getbooksbyauthor.GetBookByAuthorInput;
import com.tinqin.academy.api.book.getbooksbyauthor.GetBookByAuthorOutput;
import com.tinqin.academy.api.errors.OperationError;
import com.tinqin.academy.rest.models.LocaleHeader;
import io.vavr.control.Either;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class BookController extends BaseController {

    private final GetBook getBook;
    private final CreateBook createBook;
    private final GetBookByAuthor getBookByAuthor;
    private final LocaleHeader localeHeader;

    @GetMapping(APIRoutes.GET_BOOK)
    public ResponseEntity<?> getBook(@PathVariable("bookId") String bookId) {
        //@RequestHeader(value = "locale", required = false, defaultValue = "en") String locale) {

        GetBookInput input = GetBookInput
                .builder()
                .bookId(bookId)
                .locale(localeHeader.getLocale())
                .build();

        Either<OperationError, GetBookOutput> result = getBook.process(input);

        return mapToResponseEntity(result, HttpStatus.OK);
    }

    @PostMapping(APIRoutes.API_BOOK)
    public ResponseEntity<?> createBook(@Valid @RequestBody CreateBookInput input) {

        Either<OperationError, CreateBookOutput> result = createBook.process(input);

        return mapToResponseEntity(result, HttpStatus.CREATED);
    }

    @GetMapping(APIRoutes.API_BOOK)
    public ResponseEntity<?> getBooksByAuthor(@RequestParam("authorId") String authorId,
                                              @PageableDefault(page = 0, size = 2) Pageable pageable) {

        GetBookByAuthorInput input = GetBookByAuthorInput
                .builder()
                .authorId(authorId)
                .pageable(pageable)
                .build();

        Either<OperationError, GetBookByAuthorOutput> process = getBookByAuthor.process(input);

        return mapToResponseEntity(process, HttpStatus.OK);
    }
}
