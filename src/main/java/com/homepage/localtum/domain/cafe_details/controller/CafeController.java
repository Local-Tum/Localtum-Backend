package com.homepage.localtum.domain.cafe_details.controller;

import com.homepage.localtum.domain.cafe_details.dto.CreateCafeDto;
import com.homepage.localtum.domain.cafe_details.repository.CafeRepository;
import com.homepage.localtum.domain.cafe_details.service.CafeService;
import com.homepage.localtum.util.response.CustomApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("localtum/cafe_details")
public class CafeController {

    private final CafeService cafeService;

    public CafeController(CafeService cafeService) {
        this.cafeService = cafeService;
    }


    @PostMapping
    public ResponseEntity<CustomApiResponse<?>> createCafe(@Valid @RequestBody CreateCafeDto dto){
        ResponseEntity<CustomApiResponse<?>> result = cafeService.createCafe(dto);
        return result;
    }
    @GetMapping("/{cafe_name}")
    public ResponseEntity<CustomApiResponse<?>> getCafeById(@PathVariable("cafe_name") String cafeName){
        ResponseEntity<CustomApiResponse<?>> result =cafeService.getCafeById(cafeName);
        return result;
    }

}