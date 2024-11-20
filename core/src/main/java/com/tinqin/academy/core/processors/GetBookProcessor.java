package com.tinqin.academy.core.processors;

import com.tinqin.academy.api.operations.getbook.GetBook;
import com.tinqin.academy.api.operations.getbook.GetBookInput;
import com.tinqin.academy.api.operations.getbook.GetBookOutput;
import com.tinqin.academy.persistence.models.Book;
import com.tinqin.academy.persistence.repositories.BookRepository;

import java.util.UUID;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetBookProcessor implements GetBook {
    private final BookRepository bookRepository;

    @Override
    public GetBookOutput process(GetBookInput input) {
        Book book = fetchBook(input);
        return convertGetBookInputToGetBookOutput(book);
    }

    private Book fetchBook(GetBookInput input) {
        return bookRepository.findById(UUID.fromString(input.getBookId()))
                .orElseThrow(() -> new RuntimeException("Book not found"));

    }

    private GetBookOutput convertGetBookInputToGetBookOutput(Book book) {
        return GetBookOutput.builder()
                .author(String.valueOf(book.getAuthor()))
                .title(book.getTitle())
                .pages(book.getPages())
                .build();

    }
}
