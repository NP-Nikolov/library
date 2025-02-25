package com.tinqin.academy.rest.controllers;

import com.tinqin.academy.api.base.ProcessorResult;
import com.tinqin.academy.api.errors.OperationError;
import io.vavr.control.Either;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BaseController {

    public BaseController() {

    }

    protected <O extends ProcessorResult> ResponseEntity<?> mapToResponseEntity
            (Either<OperationError, O> either, HttpStatus httpStatus) {

        return either.isRight() ? new ResponseEntity((ProcessorResult) either.get(), httpStatus)
                : new ResponseEntity(String.format("%s: %s", ((OperationError) either.getLeft()).getErrorCode(), ((OperationError) either.getLeft()).getMessage()), ((OperationError) either.getLeft()).getStatus());
        //: new ResponseEntity(((OperationError) either.getLeft()).getStatus(), httpStatus);

    }

}
