package com.example.cardatabase2.web;

import com.example.cardatabase2.domain.Car;
import com.example.cardatabase2.domain.CarRepository;
import com.example.cardatabase2.domain.Owner;
import com.example.cardatabase2.domain.OwnerRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarController {
    private final CarRepository repository;
    private final OwnerRepository ownerRepository;

    public CarController(CarRepository repository, OwnerRepository ownerRepository) {
        this.repository = repository;
        this.ownerRepository = ownerRepository;
    }

    @GetMapping("/cars")
    public Iterable<Car> getCars() {
        // 자동차 검색 및 반환
        return repository.findAll();
    }

    @GetMapping("/owners")
    public Iterable<Owner> getOwners() {
        // 자동차 검색 및 반환
        return ownerRepository.findAll();
    }
}
