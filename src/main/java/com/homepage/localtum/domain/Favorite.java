package com.homepage.localtum.domain;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "FAVORITES")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long cafeId;
    private Long userId;

}
