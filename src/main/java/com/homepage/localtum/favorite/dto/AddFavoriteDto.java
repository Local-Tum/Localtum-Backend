package com.homepage.localtum.favorite.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class AddFavoriteDto {
    private Long userId;
    private Long cafeId;
}
