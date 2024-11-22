package com.tinqin.academy.rest.controllers;

import com.tinqin.academy.api.APIRoutes;
import com.tinqin.academy.api.book.create.CreateBook;
import com.tinqin.academy.api.book.create.CreateBookInput;
import com.tinqin.academy.api.book.create.CreateBookOutput;
import com.tinqin.academy.api.operations.getbook.GetBook;
import com.tinqin.academy.api.operations.getbook.GetBookInput;
import com.tinqin.academy.api.operations.getbook.GetBookOutput;
import com.tinqin.academy.api.postdemo.PostDemo;
import com.tinqin.academy.api.postdemo.PostDemoInput;
import com.tinqin.academy.api.postdemo.PostDemoResult;
import com.tinqin.academy.api.querydemo.QueryDemo;
import com.tinqin.academy.api.querydemo.QueryDemoInput;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class BookController {

    private final GetBook getBook;
    private final QueryDemo queryDemo;
    private final PostDemo postDemo;
    private final CreateBook createBook;

    @GetMapping(APIRoutes.GET_BOOK)
    public ResponseEntity<?> getBook(@PathVariable("bookId") String bookId) {

        GetBookInput input = GetBookInput
                .builder().
                bookId(bookId).
                build();

        GetBookOutput result = getBook.process(input);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/my-query-demo")
    // my-query-demo?myparam=hello
    public ResponseEntity<?> queryDemo(@RequestParam("myparam") String queryParam,
                                       @RequestParam("param2") String queryParam2) {
        QueryDemoInput build = QueryDemoInput
                .builder()
                .queryParam(queryParam)
                .queryParam(queryParam2)
                .build();

        queryDemo.process(build);

        return ResponseEntity.ok(queryDemo.process(build));
    }

    @PostMapping("/my-post-demo")
    public ResponseEntity<?> postDemo(@RequestBody PostDemoInput postDemoInput) {

        PostDemoResult process = postDemo.process(postDemoInput);

        return ResponseEntity.ok(postDemoInput);
    }

    @PostMapping(APIRoutes.API_BOOK)
    public ResponseEntity<?> createBook(@RequestBody CreateBookInput input) {
        CreateBookOutput result = createBook.process(input);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }


}
