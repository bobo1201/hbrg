package com.hbrg.repository;

import com.hbrg.entity.HUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("테이블 연결 테스트")
    public void createUserTest(){
        HUser hUser = new HUser();
//        hUser.setUserId(1L);
        hUser.setId("hbrg");
        hUser.setPw("hbrg1234");
        hUser.setEm("hbrg@naver.com");
        hUser.setNic("행보락규");
//        hbrg_user.setPh(010123456L);
//        hbrg_user.setAd("12315646");
        HUser savedHUser = userRepository.save(hUser);
        System.out.println(savedHUser.toString());
    }
}