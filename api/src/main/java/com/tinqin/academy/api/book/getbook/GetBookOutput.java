package com.tinqin.academy.api.book.getbook;

import com.tinqin.academy.api.base.ProcessorResult;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class GetBookOutput implements ProcessorResult {

    private String author;
    private String title;
    private String pages;

}
