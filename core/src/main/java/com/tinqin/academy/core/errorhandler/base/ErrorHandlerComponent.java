package com.tinqin.academy.core.errorhandler.base;

import com.tinqin.academy.api.errors.OperationError;

public interface ErrorHandlerComponent {
    OperationError handle(Throwable throwable);

    ErrorHandlerComponent getNext();

    void setNext(ErrorHandlerComponent next);
}
