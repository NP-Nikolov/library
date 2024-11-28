package com.tinqin.academy.core.errorhandler.base;

import com.tinqin.academy.api.errors.OperationError;

public interface ErrorHandler {
    OperationError handle(Throwable throwable);
}
