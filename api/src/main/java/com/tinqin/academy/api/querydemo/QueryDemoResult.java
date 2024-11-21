package com.tinqin.academy.api.querydemo;

import com.tinqin.academy.api.base.ProcessorResult;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class QueryDemoResult implements ProcessorResult {

    private final String resultMessage;

    public String getResultMessage() {
        return resultMessage;
    }
}
