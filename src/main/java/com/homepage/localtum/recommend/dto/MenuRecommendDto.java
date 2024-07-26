package com.homepage.localtum.recommend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MenuRecommendDto {
    private String mood;
    private List<RecommendDto> recommendedMenu;
}