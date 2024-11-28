package com.tinqin.academy.core.errorhandler.components;

import com.tinqin.academy.api.errors.BeError;
import com.tinqin.academy.api.errors.OperationError;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class InternalErrorHandlerComponent extends BaseErrorHandlerComponent {
    @Override
    public OperationError handle(Throwable throwable) {
        return BeError
                .builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .errorCode("IE-001")
                .message(throwable.getMessage())
                .build();
    }
}
