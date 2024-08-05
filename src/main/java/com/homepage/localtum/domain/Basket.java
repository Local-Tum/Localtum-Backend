package com.homepage.localtum.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Entity
@Table(name = "BASKETS")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Basket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "basketmenu", nullable = false)
    private String basketMenu;

    @Column(name = "price", nullable = false)
    private int prices;
    private String memberId;
    private String member;
    private String size;
    private String status;
    private String cafename;

    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> options;
}
