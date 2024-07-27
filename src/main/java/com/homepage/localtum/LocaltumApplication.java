package com.homepage.localtum;

import com.homepage.localtum.domain.Cafe;
import com.homepage.localtum.cafe_details.repository.CafeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class LocaltumApplication implements CommandLineRunner {

	@Autowired
	private CafeRepository cafeRepository;

	public static void main(String[] args) {
		SpringApplication.run(LocaltumApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		cafeRepository.save(Cafe.builder()
				.name("에크하우스")
				.menu(Arrays.asList("콜드브루", "카푸치노", "플랫화이트", "하우스 돌체", "카페라떼"))
				.build());

		cafeRepository.save(Cafe.builder()
				.name("카페도킹")
				.menu(Arrays.asList("아메리카노", "카페라떼", "로얄밀크티", "초코라떼"))
				.build());

		cafeRepository.save(Cafe.builder()
				.name("C")
				.menu(Arrays.asList("c1", "c2"))
				.build());
	}
}
