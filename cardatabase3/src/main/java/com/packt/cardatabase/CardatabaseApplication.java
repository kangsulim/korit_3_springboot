package com.packt.cardatabase;

import com.packt.cardatabase.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.packt.cardatabase.domain.CarRepository;

import java.util.Arrays;

@SpringBootApplication
public class CardatabaseApplication implements CommandLineRunner {
	private static final Logger logger = LoggerFactory.getLogger(CardatabaseApplication.class);

	// 생성자를 통한 의존성 주입, CarRepository
	private final CarRepository repository;	// 필드로 객체 선언
	private final OnwerRepository onwerRepository;

	public CardatabaseApplication(CarRepository repository, OnwerRepository onwerRepository) {	// 여기서 생성자를 통해 의존성 주입
		this.repository = repository;
		this.onwerRepository = onwerRepository;
	}

	public static void main(String[] args) {

		SpringApplication.run(CardatabaseApplication.class, args);

		logger.info("애플리케이션 실행");
	}

	@Override
	public void run(String... args) throws Exception {
		// Owner 객체 생성
		Owner owner1 = new Owner("John", "Johnson");
		Owner owner2 = new Owner("Mary", "Robinson");
		Owner owner3 = new Owner("근수", "안");
		onwerRepository.saveAll(Arrays.asList(owner1, owner2, owner3));

		
		repository.save(new Car("Ford", "Mustang", "Red", "ADF-11121", 2023, 59000, owner1));
		repository.save(new Car("Nissan", "Leaf", "White", "SSJ-3002", 2020, 29000, owner1));
		repository.save(new Car("Toyata", "Pruis", "Silver", "KKO-0212", 2022, 39000, owner2));
		repository.save(new Car("Kia", "Seltos", "Chacoal", "360수5690", 2020, 28000, owner3));

		// 모든 자동차 엔티티를 다 찾아서 Console 창에 로깅
		for (Car car : repository.findAll()) {
			logger.info("브랜드: {}, 모델명: {}", car.getBrand(), car.getModel());
		}
	}
}
