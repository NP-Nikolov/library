package com.tinqin.academy.persistence.filereader;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Builder
public class BookModel {
    private final String title;
    private final String pages;
    private final BigDecimal price;
    private final String authorFirstName;
    private final String authorLastName;
}
