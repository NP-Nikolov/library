package com.tinqin.academy.api.postdemo;

import com.tinqin.academy.api.base.ProcessorResult;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class PostDemoResult implements ProcessorResult {
    private final String resultMessage;
}
