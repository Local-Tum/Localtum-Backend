package com.homepage.localtum.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "COPUONS")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cafe_name;
    private int Coupon_description;
    private String Member_name;

}
