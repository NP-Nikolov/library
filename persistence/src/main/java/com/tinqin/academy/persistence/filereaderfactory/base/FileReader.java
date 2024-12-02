package com.tinqin.academy.persistence.filereaderfactory.base;

import com.tinqin.academy.persistence.filereaderfactory.models.BookModel;

import java.util.List;

public interface FileReader {

    List<BookModel> getBatch();
}
