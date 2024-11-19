package com.tinqin.academy.api.base;

public interface Processor<R extends ProcessorResult, I extends ProcessorInput> {
    R process(I input);
}
