package com.hbrg.repository;

import com.hbrg.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class UserRepositoryTest {
    @Autowired
    UserRepository hbrg_userRepository;

    @Test
    @DisplayName("테이블 연결 테스트")
    public void createUserTest(){
        User hbrg_user = new User();
//        hbrg_user.setHbrgIdNum(1L);
        hbrg_user.setHbrgId("hbrg");
        hbrg_user.setHbrgPw("hbrg1234");
        hbrg_user.setHbrgEm("hbrg@naver.com");
        hbrg_user.setHbrgNic("행보락규");
//        hbrg_user.setHbrgPh(010123456L);
//        hbrg_user.setHbrgAd("12315646");
        User savedHbrgUser = hbrg_userRepository.save(hbrg_user);
        System.out.println(savedHbrgUser.toString());
    }
}