package com.example.cardatabase;

import com.example.cardatabase.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Cardatabase2Application implements CommandLineRunner {
	private static final Logger logger = LoggerFactory.getLogger(Cardatabase2Application.class);

	private final CarRepository repository;
	private final OwnerRepository oRepository;
	private final AppUserRepository uRepository;
    private String[] args;

    public Cardatabase2Application(CarRepository repository, OwnerRepository oRepository, AppUserRepository uRepository) {
		this.repository = repository;
		this.oRepository = oRepository;
        this.uRepository = uRepository;
    }

	public static void main(String[] args) {
		
		SpringApplication.run(Cardatabase2Application.class, args);
		
		logger.info("애플리케이션 실행");
	}

	@Override
	public void run(String... args) throws Exception {
		Owner owner1 = oRepository.save(new Owner("John", "Johnson"));
		Owner owner2 = oRepository.save(new Owner("Mary", "Robinson"));
		Owner owner3 = oRepository.save(new Owner("근수", "안"));

		repository.save(new Car("Ford", "Mustang", "Red", "ADF-11121", 2023, 59000, owner1));
		repository.save(new Car("Nissan", "Leaf", "White", "SSJ-3002", 2020, 29000, owner1));
		repository.save(new Car("Toyata", "Pruis", "Silver", "KKO-0212", 2022, 39000, owner2));
		repository.save(new Car("Kia", "Seltos", "Chacoal", "360수5690", 2020, 28000, owner3));

		for (Car car : repository.findAll()) {
			logger.info("브랜드: {}, 모델명: {}", car.getBrand(), car.getModel());
		}

		// 사용자명: user, 비밀번호: user
		AppUser user1 = uRepository.save(new AppUser("user", "$2y$04$3Al8xee5DklaCIC3fGTI5OnvBKITNy3AWmDpAeeMc6B2Mz6aGDozu", "USER"));
		// 사용자명: admin, 비밀번호: admin
		AppUser user2 = uRepository.save(new AppUser("admin", "$2y$04$.dGdzGkkWgW4jjGUAuWVF.UtPvdjJL05cDCycAtToYdz2bIrWf8XW", "ADMIN"));
	}
}
