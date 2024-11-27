package com.tinqin.academy.api.errors;

import com.tinqin.academy.api.enumerations.MessageLevel;
import org.springframework.http.HttpStatus;

public interface OperationError {

    HttpStatus getStatus();

    String getErrorCode();

    String getMessage();

    MessageLevel getMessageLevel();
}
