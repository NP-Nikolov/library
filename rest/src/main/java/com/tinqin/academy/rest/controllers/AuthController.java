package com.tinqin.academy.rest.controllers;

import com.tinqin.academy.api.auth.login.LoginUserInput;
import com.tinqin.academy.api.auth.register.RegisterUser;
import com.tinqin.academy.api.auth.register.RegisterUserInput;
import com.tinqin.academy.api.auth.register.RegisterUserResult;
import com.tinqin.academy.api.errors.OperationError;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.tinqin.academy.api.APIRoutes.LOGIN;
import static com.tinqin.academy.api.APIRoutes.REGISTER;

@RestController
@RequiredArgsConstructor
public class AuthController extends BaseController {
    private final RegisterUser registerUser;

    @PostMapping(REGISTER)
    public ResponseEntity<?> register(@RequestBody RegisterUserInput input) {
        Either<OperationError, RegisterUserResult> results = registerUser.process(input);

        return mapToResponseEntity(results, HttpStatus.CREATED);
    }

    @PostMapping(LOGIN)
    public ResponseEntity<?> login(@RequestBody LoginUserInput input) {
        return ResponseEntity.ok("User registered");
    }
}
