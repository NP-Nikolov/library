package com.tinqin.academy.api.book.create;

import com.tinqin.academy.api.base.ProcessorResult;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class CreateBookOutput implements ProcessorResult {

    private UUID bookId;

}
