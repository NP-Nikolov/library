package com.tinqin.academy.core.user;

import com.tinqin.academy.api.errors.OperationError;
import com.tinqin.academy.api.user.BlockUser;
import com.tinqin.academy.api.user.BlockUserInput;
import com.tinqin.academy.api.user.BlockUserOutput;
import com.tinqin.academy.core.errorhandler.base.ErrorHandler;
import com.tinqin.academy.persistence.models.User;
import com.tinqin.academy.persistence.repositories.UserRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.tinqin.academy.api.ValidationMessages.*;

@Service
@RequiredArgsConstructor
public class UserBlockProcessor implements BlockUser {

    private final UserRepository userRepository;
    private final ErrorHandler errorHandler;

    @Override
    public Either<OperationError, BlockUserOutput> process(BlockUserInput input) {
        return
                validateAdmin(UUID.fromString(input.getId()))
                        .flatMap(adminId -> validateUnblockedUser(UUID.fromString(input.getId())))
                        .flatMap(this::blockUserAndSave)
                        .map(this::convertUserToBlockUserOutput)
                        .toEither()
                        .mapLeft(errorHandler::handle);
    }

    private Try<UUID> validateAdmin(UUID adminId) {
        return Try.of(() -> userRepository.findById(adminId)
                .filter(user -> !user.isAdmin())
                .map(User::getId)
                .orElseThrow(
                        () -> new IllegalArgumentException(USER_IS_ADMIN)));
    }

    private Try<UUID> validateUnblockedUser(UUID userId) {
        return Try.of(() -> userRepository.findUnblockUserId(userId)
                .orElseThrow(
                        () -> new IllegalStateException(USER_IS_ALREADY_BLOCK)));
    }

    private Try<User> blockUserAndSave(UUID userId) {
        return Try.of(() -> {
            User userToBlock = userRepository.findById(userId)
                    .orElseThrow(() -> new IllegalArgumentException(USER_IS_NOT_FOUND));
            userToBlock.setBlocked(true);
            userRepository.save(userToBlock);
            return userToBlock;
        });
    }

    private BlockUserOutput convertUserToBlockUserOutput(User user) {
        user.setBlocked(true);
        userRepository.save(user);
        return BlockUserOutput
                .builder()
                .build();
    }
}
