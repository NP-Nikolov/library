package com.tinqin.academy.rest.controllers;

import com.tinqin.academy.api.APIRoutes;
import com.tinqin.academy.api.book.create.CreateBook;
import com.tinqin.academy.api.book.create.CreateBookInput;
import com.tinqin.academy.api.book.create.CreateBookOutput;
import com.tinqin.academy.api.errors.OperationError;
import com.tinqin.academy.api.operations.getbook.GetBook;
import com.tinqin.academy.api.operations.getbook.GetBookInput;
import com.tinqin.academy.api.operations.getbook.GetBookOutput;
import io.vavr.control.Either;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class BookController extends BaseController {

    private final GetBook getBook;
    private final CreateBook createBook;

    @GetMapping(APIRoutes.GET_BOOK)
    public ResponseEntity<?> getBook(@PathVariable("bookId") String bookId) {

        GetBookInput input = GetBookInput
                .builder().
                bookId(bookId).
                build();

        Either<OperationError, GetBookOutput> result = getBook.process(input);
        return mapToResponseEntity(result, HttpStatus.OK);
    }

    @PostMapping(APIRoutes.API_BOOK)
    public ResponseEntity<?> createBook(@Valid @RequestBody CreateBookInput input) {
        Either<OperationError, CreateBookOutput> result = createBook.process(input);
        return mapToResponseEntity(result, HttpStatus.CREATED);
    }

}
