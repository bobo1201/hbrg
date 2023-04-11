package com.hbrg.repository;

import com.hbrg.entity.Hbrg_User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class Hbrg_UserRepositoryTest {
    @Autowired
    Hbrg_UserRepository hbrg_userRepository;

    @Test
    @DisplayName("테이블 연결 테스트")
    public void createUserTest(){
        Hbrg_User hbrg_user = new Hbrg_User();
        hbrg_user.setUserId(1L);
        hbrg_user.setId("hbrg");
        hbrg_user.setPw("hbrg1234");
        hbrg_user.setEm("hbrg@naver.com");
        hbrg_user.setNic("행보락규");
//        hbrg_user.setPh(010123456L);
//        hbrg_user.setAd("12315646");
        Hbrg_User savedHbrgUser = hbrg_userRepository.save(hbrg_user);
        System.out.println(savedHbrgUser.toString());
    }
}