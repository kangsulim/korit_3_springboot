package com.example.cardatabase2;

import com.example.cardatabase2.domain.Owner;
import com.example.cardatabase2.domain.OwnerRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class OwnerRepositoryTest {
    @Autowired
    private OwnerRepository repository;

    @Test
    @DisplayName("테스트1 : Owner 객체 저장")
    void saveOwner() {
        repository.save(new Owner("일", "김"));       // main에서 쓴 예제는 사용 불가!!
        assertThat(repository.findByFirstname("일").isPresent()).isTrue();
    }

    @Test
    @DisplayName("테스트2 : Owner 객체 삭제")
    void deleteOwners() {
        repository.save(new Owner("이", "김"));
        repository.deleteAll();
        assertThat(repository.count()).isEqualTo(0);
    }
}