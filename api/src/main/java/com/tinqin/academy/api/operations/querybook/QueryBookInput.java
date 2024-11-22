package com.tinqin.academy.api.operations.querybook;

import com.tinqin.academy.api.base.ProcessorInput;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class QueryBookInput implements ProcessorInput {
    private String queryBookTitle;
    private String queryBookPages;

}
