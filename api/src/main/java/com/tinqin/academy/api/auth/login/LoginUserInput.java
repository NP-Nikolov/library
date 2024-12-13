package com.tinqin.academy.api.auth.login;

import com.tinqin.academy.api.base.ProcessorInput;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginUserInput implements ProcessorInput {

    @NotBlank
    @Length(max = 100)
    private String username;

    @NotBlank
    @Length(max = 100)
    private String password;
}
