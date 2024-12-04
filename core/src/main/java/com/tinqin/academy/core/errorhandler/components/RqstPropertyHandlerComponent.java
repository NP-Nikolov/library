package com.tinqin.academy.core.errorhandler.components;

import com.tinqin.academy.api.enumerations.MessageLevel;
import com.tinqin.academy.api.errors.BeError;
import com.tinqin.academy.api.errors.OperationError;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class RqstPropertyHandlerComponent extends BaseErrorHandlerComponent {
    @Override
    public OperationError handle(Throwable throwable) {
        if (throwable instanceof PropertyReferenceException exception) {
            return BeError
                    .builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .errorCode("BE-002")
                    .message(exception.getMessage())
                    .messageLevel(MessageLevel.ERROR)
                    .build();
        }
        return getNext().handle(throwable);
    }
}
