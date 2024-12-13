package com.tinqin.academy.core.processors.auth;

import com.tinqin.academy.api.auth.register.RegisterUser;
import com.tinqin.academy.api.auth.register.RegisterUserInput;
import com.tinqin.academy.api.auth.register.RegisterUserResult;
import com.tinqin.academy.api.errors.OperationError;
import com.tinqin.academy.core.errorhandler.base.ErrorHandler;
import com.tinqin.academy.persistence.models.User;
import com.tinqin.academy.persistence.repositories.UserRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterUserOperation implements RegisterUser {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final ErrorHandler errorHandler;

    @Override
    public Either<OperationError, RegisterUserResult> process(RegisterUserInput input) {
        return Try.of(() -> {
                    User user = User
                            .builder()
                            .firstName(input.getFirstName())
                            .lastName(input.getLastName())
                            .isAdmin(false)
                            .isBlocked(false)
                            .password(passwordEncoder.encode(input.getPassword()))
                            .username(input.getUsername())
                            .build();

                    userRepository.save(user);

                    return RegisterUserResult
                            .builder()
                            .build();
                })
                .toEither()
                .mapLeft(errorHandler::handle);
    }
}
