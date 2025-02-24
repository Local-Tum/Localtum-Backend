package com.homepage.localtum.cafe.dto;

import com.homepage.localtum.domain.Cafe;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.FetchType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class CreateCafeDto {

    @NotBlank(message = "카페이름을 입력해주세요")
    private String name;

    private String address;

    @NotNull
    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> menu;
    public Cafe toEntity(){
        return Cafe.builder()
                .name(name)
                .menu(menu)
                .address(address)
                .build();
    }
}
