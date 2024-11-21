package com.tinqin.academy.core.processors;

import com.tinqin.academy.api.querydemo.QueryDemoInput;
import com.tinqin.academy.api.querydemo.QueryDemoResult;
import org.springframework.stereotype.Service;

@Service
public class QueryDemo implements com.tinqin.academy.api.querydemo.QueryDemo{
    @Override
    public QueryDemoResult process(QueryDemoInput input) {

        return QueryDemoResult
                .builder()
                .resultMessage("My query contains the following params: " + input.getQueryParam())
                .build();
    }
}
