package com.tinqin.academy.api.operations.querybook;

import com.tinqin.academy.api.base.ProcessorResult;
import com.tinqin.academy.api.models.BookModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class QueryBookResult implements ProcessorResult {
    private List<BookModel> books;
}
