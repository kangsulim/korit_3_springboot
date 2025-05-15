package com.packt.cardatabase.domain;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CarRepository extends CrudRepository<Car, Long> {
//    // 브랜드로 자동차 검색
//    @Query("select c from Car c where c.brand=?1")
//    List<Car> findByBrand(String brand); // 인터페이스이기 때문에 접근지정자가 없다(public) + 구현부가 없다(나중에 할 거다)
//
//    // 새상으로 자동차 검색
//    List<Car> findByColor(String color);
//
//    // 생산년도로 자동차 검색
//    List<Car> findByModelYear(int modelYear);
//
//    // 브랜드와 모델로 자동차 검색
//    List<Car> findByBrandAndModel(String brand, String model);
//
//    // 브랜드 또는 색상별로 자동차 검색
//    List<Car> findByBrandOrColor(String brand, String color);
//
//    // 브랜드로 자동차 검색, 연도로 정렬
//    List<Car> findByBrandOrderByModelYearAsc(String brand);
//
//    // SQL문을 이용하여 브랜드로 자동차 검색, like 활용
//    @Query("select c from Car c where brand like %1")
//    List<Car> findByBrandEndsWith(String brand);
}
