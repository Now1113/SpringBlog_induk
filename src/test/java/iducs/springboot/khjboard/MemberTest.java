package iducs.springboot.khjboard;

import iducs.springboot.khjboard.entity.MemberEntity;
import iducs.springboot.khjboard.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;
import java.util.stream.IntStream;

@SpringBootTest
class MemberTest {
    //POJO (Plain Old Java Object) : 가장 기본적인 자바 객체 형태 - 필드, getter, setter
    //Beans 규약을 준수, 생성자가 복잡하지 않음.
    //DI(Dependency Injection) : 의존성 주입, Spring Framework 가 의존성을 해결하는 방법

    @Autowired
    MemberRepository memberRepository;

    @Test
    void testAdmin() {
        // Integer 데이터 흐름, 람다식 - 함수형 언어의 특징을 사용
        String str = "admin";
        MemberEntity entity = MemberEntity.builder()
                .id(str)
                .pw(str)
                .name("name-" + str)
                .email(str + "@induk.ac.kr")
                .phone("phone-" + new Random().nextInt(50))
                .address("address-" + new Random().nextInt(50))
                .access("access")
                .level("1")
                .category("공학계")
                .build();
        memberRepository.save(entity);
    }
}
