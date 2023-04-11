package com.hbrg.service;

import com.hbrg.dto.Hbrg_UserDto;
import com.hbrg.dto.Hbrg_UserFormDto;
import com.hbrg.entity.Hbrg_User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
public class Hbrg_UserServiceTest {

    @Autowired
    Hbrg_UserService hbrg_userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Hbrg_User createHbrg_User(){
        Hbrg_UserFormDto hbrg_userFormDto = new Hbrg_UserFormDto();
        hbrg_userFormDto.setId("test");
        hbrg_userFormDto.setPw("1234");
        hbrg_userFormDto.setEm("test@test.com");
        hbrg_userFormDto.setNic("테스트");
        hbrg_userFormDto.setPh(0140L);
        hbrg_userFormDto.setAd("김해시");

        return Hbrg_User.createHbrg_User(hbrg_userFormDto, passwordEncoder);

    }

    @Test
    @DisplayName("회원가입 테스트")
    public void saveHbrg_UserTest(){
        Hbrg_User hbrg_user = createHbrg_User();
        Hbrg_User savedHbrg_User = hbrg_userService.saveHbrg_User(hbrg_user);

        assertEquals(hbrg_user.getId(), savedHbrg_User.getId());
        assertEquals(hbrg_user.getPw(), savedHbrg_User.getPw());
        assertEquals(hbrg_user.getEm(), savedHbrg_User.getEm());
        assertEquals(hbrg_user.getNic(), savedHbrg_User.getNic());
        assertEquals(hbrg_user.getPh(), savedHbrg_User.getPh());
        assertEquals(hbrg_user.getAd(), savedHbrg_User.getAd());

    }
}
