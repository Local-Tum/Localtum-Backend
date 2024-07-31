package com.homepage.localtum.cafe.service;

import com.homepage.localtum.cafe.dto.CreateCafeDto;
import com.homepage.localtum.cafe.dto.GetCafeDetailsDto;
import com.homepage.localtum.domain.Cafe;
import com.homepage.localtum.cafe.repository.CafeRepository;
import com.homepage.localtum.domain.Member;
import com.homepage.localtum.domain.Stamp;
import com.homepage.localtum.member.repository.MemberRepository;
import com.homepage.localtum.stamp.StampRepository;
import com.homepage.localtum.util.response.CustomApiResponse;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
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
    private final MemberRepository memberRepository;
    private final StampRepository stampRepository;

//    public ResponseEntity<CustomApiResponse<?>> createCafe(CreateCafeDto dto){
//        Cafe cafe =dto.toEntity();
//        Cafe save = cafeRepository.save(cafe);
//        CustomApiResponse<CreateCafeDto> result = CustomApiResponse.createSuccess(HttpStatus.OK.value(), null,"데이터가 저장되었습니다");
//        return ResponseEntity.ok(result);
//    }
//
//    public ResponseEntity<CustomApiResponse<?>> getCafeById(String cafeName){
//        Optional<Cafe> cafe = cafeRepository.findByName(cafeName);
//        if(cafe.isEmpty()){
//            CustomApiResponse<Void> response = CustomApiResponse.createFailWithout(HttpStatus.NOT_FOUND.value(), "해당 계시글을 찾을수 없습니다.");
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
//        }
//        Cafe cafeDto = cafe.get();
//        List<String> menu =cafeDto.getMenu();
//        GetCafeDetailsDto caferesponse =new GetCafeDetailsDto(
//                cafeDto.getId(),
//                cafeDto.getName(),
//                menu
//        );
//        CustomApiResponse<GetCafeDetailsDto> response = CustomApiResponse.createSuccess(HttpStatus.OK.value(), caferesponse ,"성공적으로 조회되었습니다.");
//        return ResponseEntity.ok(response);
//    }
//
//    public List<Cafe> getAllCafes() {
//        return cafeRepository.findAll();
//    }
//
//    public Cafe getCafeById(Long id) {
//        return cafeRepository.findById(id).orElse(null);
//    }

    // 스탬프 조회
    public ResponseEntity<CustomApiResponse<?>> getStamps(String memberId, String cafename) {
        Optional<Member> optionalMember = memberRepository.findByMemberId(memberId);

        if (optionalMember.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(CustomApiResponse.createFailWithoutData(HttpStatus.NOT_FOUND.value(), "아이디가 " + memberId + "인 회원은 존재하지 않습니다."));
        }

        Optional<Stamp> optionalStamp = stampRepository.findByMemberIdAndCafename(memberId, cafename);

        if (optionalStamp.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(CustomApiResponse.createFailWithoutData(HttpStatus.NOT_FOUND.value(), cafename + "에 대한 스탬프 정보가 없습니다."));
        }

        Stamp stamp = optionalStamp.get();
        CustomApiResponse<Stamp> result = CustomApiResponse.createSuccess(HttpStatus.OK.value(), stamp, "스탬프 정보 조회 완료");
        return ResponseEntity.ok(result);
    }
}
