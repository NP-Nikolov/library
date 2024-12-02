package com.tinqin.academy.persistence.filereaderfactory;

import com.tinqin.academy.persistence.filereaderfactory.base.FileReader;
import com.tinqin.academy.persistence.filereaderfactory.readers.CsvFileReader;
import com.tinqin.academy.persistence.filereaderfactory.readers.JsonFileReader;

public abstract class FileReaderFactory {

    public static FileReader createCsvFileReader(String path, Integer batchSize) {
        return new CsvFileReader(path, batchSize);
    }

    public static FileReader createJsonFileReader(String path, Integer batchSize) {
        return new JsonFileReader(path, batchSize);
    }
}