package com.homepage.localtum.favorite.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class AddFavoriteDto {
    private String userName;
    private String cafeName;
}
