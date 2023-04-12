package com.hbrg.entity;

import com.hbrg.constant.Role;
import com.hbrg.dto.Hbrg_UserFormDto;
import com.hbrg.service.Hbrg_UserService;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;


import javax.persistence.*;

@Entity
@Table(name = "Hbrg_User")
@Getter
@Setter
@ToString
public class Hbrg_User {

//    @Column(name="userId")
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long userId; // 아이디 순서

    @Id
    @Column(name="id", nullable = false)
    private String id; // 로그인 ID


    @Column(name="pw", nullable = false)
    private String pw; // 로그인 PW

    @Column(name="em", nullable = false)
    private String em; // 회원가입 이메일

    @Column(name="nic", nullable = false)
    private String nic; // 회원가입 닉네임

    @Column(name="ph")
    private Long ph; // 회원가입 전화번호

    @Column(name="ad")
    private String ad; // 회원가입 주소

    @Enumerated(EnumType.STRING)
    private Role role;

    public static Hbrg_User createHbrg_User (Hbrg_UserFormDto hbrg_userFormDto, PasswordEncoder passwordEncoder){
        Hbrg_User hbrg_user = new Hbrg_User();
        hbrg_user.setId(hbrg_userFormDto.getId());
        hbrg_user.setPw(hbrg_userFormDto.getPw());
        hbrg_user.setEm(hbrg_userFormDto.getEm());
        hbrg_user.setNic(hbrg_userFormDto.getNic());
        hbrg_user.setPh(hbrg_userFormDto.getPh());
        hbrg_user.setAd(hbrg_userFormDto.getAd());
        hbrg_user.setRole(Role.USER);

        //SecurityConfig의 BCryptPasswordEncoder Bean을 파라미터로 넘겨서 비밀번호 암호화
        String Password = passwordEncoder.encode(hbrg_userFormDto.getPw());
        hbrg_user.setPw(Password);

        return hbrg_user;
    }
}
