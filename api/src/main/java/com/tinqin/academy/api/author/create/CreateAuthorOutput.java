package com.tinqin.academy.api.author.create;

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
public class CreateAuthorOutput implements ProcessorResult {

    private UUID authorId;

}
