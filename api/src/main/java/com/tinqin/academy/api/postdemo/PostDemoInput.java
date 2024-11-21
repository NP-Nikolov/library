package com.tinqin.academy.api.postdemo;

import com.tinqin.academy.api.base.ProcessorInput;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class PostDemoInput implements ProcessorInput {

    private String message;

}
