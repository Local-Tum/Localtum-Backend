package com.homepage.localtum.member.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SignInResDto {
    private String accessToken;
}