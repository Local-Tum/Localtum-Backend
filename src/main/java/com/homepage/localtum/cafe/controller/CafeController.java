package com.homepage.localtum.cafe.controller;

import com.homepage.localtum.cafe.dto.CreateCafeDto;
import com.homepage.localtum.cafe.service.CafeService;
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