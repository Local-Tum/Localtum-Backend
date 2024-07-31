package com.homepage.localtum.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.bind.annotation.Mapping;

import java.util.List;

@Entity
@Table(name = "ORDERS")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "menu", nullable = false)
    private String orderMenu;

    @Column(name = "price", nullable = false)
    private int prices;

    private String member;

    private String size;

    private String status;

    private String cafename;

    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> options;
}
