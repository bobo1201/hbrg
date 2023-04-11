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
<<<<<<< HEAD:src/test/java/com/hbrg/repository/UserRepositoryTest.java
        User hbrg_user = new User();
//        hbrg_user.setHbrgIdNum(1L);
        hbrg_user.setHbrgId("hbrg");
        hbrg_user.setHbrgPw("hbrg1234");
        hbrg_user.setHbrgEm("hbrg@naver.com");
        hbrg_user.setHbrgNic("행보락규");
//        hbrg_user.setHbrgPh(010123456L);
//        hbrg_user.setHbrgAd("12315646");
        User savedHbrgUser = hbrg_userRepository.save(hbrg_user);
=======
        Hbrg_User hbrg_user = new Hbrg_User();
        hbrg_user.setUserId(1L);
        hbrg_user.setId("hbrg");
        hbrg_user.setPw("hbrg1234");
        hbrg_user.setEm("hbrg@naver.com");
        hbrg_user.setNic("행보락규");
//        hbrg_user.setPh(010123456L);
//        hbrg_user.setAd("12315646");
        Hbrg_User savedHbrgUser = hbrg_userRepository.save(hbrg_user);
>>>>>>> main:src/test/java/com/hbrg/repository/Hbrg_UserRepositoryTest.java
        System.out.println(savedHbrgUser.toString());
    }
}