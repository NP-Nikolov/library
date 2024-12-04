package com.tinqin.academy.api.book.getbooksbyauthor;

import com.tinqin.academy.api.base.ProcessorResult;
import com.tinqin.academy.api.models.BookModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetBookByAuthorOutput implements ProcessorResult {

    private List<BookModel> books;
}
