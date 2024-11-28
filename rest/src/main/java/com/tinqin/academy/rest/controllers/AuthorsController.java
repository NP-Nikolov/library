package com.tinqin.academy.rest.controllers;

import com.tinqin.academy.api.author.create.CreateAuthor;
import com.tinqin.academy.api.author.create.CreateAuthorInput;
import com.tinqin.academy.api.author.create.CreateAuthorOutput;
import com.tinqin.academy.api.errors.OperationError;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.vavr.control.Either;
//import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.tinqin.academy.api.APIRoutes.API_AUTHOR;

@RestController
@RequiredArgsConstructor
public class AuthorsController extends BaseController {
    private final CreateAuthor createAuthor;

    @PostMapping(API_AUTHOR)
    @Operation(summary = "Create an author",
            description = "Create an author and return UUID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    public ResponseEntity<?> createAuthor(@RequestBody CreateAuthorInput input) {
        Either<OperationError, CreateAuthorOutput> result = createAuthor.process(input);

        return mapToResponseEntity(result, HttpStatus.CREATED);
    }
}
