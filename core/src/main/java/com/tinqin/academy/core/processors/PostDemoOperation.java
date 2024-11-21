package com.tinqin.academy.core.processors;

import com.tinqin.academy.api.postdemo.PostDemo;
import com.tinqin.academy.api.postdemo.PostDemoInput;
import com.tinqin.academy.api.postdemo.PostDemoResult;
import org.springframework.stereotype.Service;

@Service
public class PostDemoOperation implements PostDemo {
    @Override
    public PostDemoResult process(PostDemoInput input) {
        PostDemoResult build = PostDemoResult
                .builder()
                //.resultMessage("My post contains the following message: " + input.getMessage())
                .build();

        return build;
    }
}
