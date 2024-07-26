package com.homepage.localtum.domain.cafe_details.service;

import com.homepage.localtum.domain.cafe_details.dto.CreateCafeDto;
import com.homepage.localtum.domain.cafe_details.dto.GetCafeDetailsDto;
import com.homepage.localtum.domain.cafe_details.entitiy.Cafe;
import com.homepage.localtum.domain.cafe_details.repository.CafeRepository;
import com.homepage.localtum.util.response.CustomApiResponse;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Builder
public class CafeService {
    public final CafeRepository cafeRepository;

    public ResponseEntity<CustomApiResponse<?>> createCafe(CreateCafeDto dto){
        Cafe cafe =dto.toEntity();
        Cafe save = cafeRepository.save(cafe);
        CustomApiResponse<CreateCafeDto> result = CustomApiResponse.createSuccess(HttpStatus.OK.value(), null,"데이터가 저장되었습니다");
        return ResponseEntity.ok(result);
    }
    public ResponseEntity<CustomApiResponse<?>> getCafeById(String cafeName){
        Optional<Cafe> cafe = cafeRepository.findByName(cafeName);
        if(cafe.isEmpty()){
            CustomApiResponse<Void> response = CustomApiResponse.createFailWithout(HttpStatus.NOT_FOUND.value(), "해당 계시글을 찾을수 없습니다.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        Cafe cafeDto = cafe.get();
        List<String> menu =cafeDto.getMenu();
        GetCafeDetailsDto caferesponse =new GetCafeDetailsDto(
                cafeDto.getId(),
                cafeDto.getName(),
                menu
        );
        CustomApiResponse<GetCafeDetailsDto> response = CustomApiResponse.createSuccess(HttpStatus.OK.value(), caferesponse ,"성공적으로 조회되었습니다.");
        return ResponseEntity.ok(response);
    }
}
