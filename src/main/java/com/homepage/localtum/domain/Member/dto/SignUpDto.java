package com.homepage.localtum.domain.Member.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SignUpDto {
    @NotNull
    private String memberId;
    @NotNull
    private String password;
    @NotNull
    private String nickname;
    @NotNull
    List<String> memberRoles;

}