package com.homepage.localtum.cafe.dto;

import lombok.*;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GetCafeDetailsDto {

        private Long id;
        private String name;
        private List<String> menu;
}
