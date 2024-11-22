package com.tinqin.academy.rest.controllers;

import com.tinqin.academy.api.author.create.CreateAuthor;
import com.tinqin.academy.api.author.create.CreateAuthorInput;
import com.tinqin.academy.api.author.create.CreateAuthorOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.tinqin.academy.api.APIRoutes.API_AUTHOR;

@RestController
@RequiredArgsConstructor
public class AuthorsController {
    private final CreateAuthor createAuthor;

    @PostMapping(API_AUTHOR)
    public ResponseEntity<?> createAuthor(@RequestBody CreateAuthorInput input) {
        CreateAuthorOutput result = createAuthor.process(input);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}
