package com.homepage.localtum.order.dto;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.FetchType;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class AddOrderDto {
    private String menu;
    @NotBlank
    private String size;
    @NotBlank
    private String status;
    private int prices;
    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> options;
}
