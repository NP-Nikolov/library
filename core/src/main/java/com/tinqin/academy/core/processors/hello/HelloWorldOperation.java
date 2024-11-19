package com.tinqin.academy.core.processors.hello;

import com.tinqin.academy.api.hello.HelloWorld;
import com.tinqin.academy.api.hello.HelloWorldInput;
import com.tinqin.academy.api.hello.HelloWorldResult;
import org.springframework.stereotype.Service;

@Service
public class HelloWorldOperation implements HelloWorld {
    @Override
    public HelloWorldResult process(HelloWorldInput input) {
        return null;
    }
}
