package com.hbrg.entity;

import com.hbrg.constant.Role;
import com.hbrg.dto.UserFormDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;


import javax.persistence.*;

@Entity
@Table(name = "HUser")
@Getter
@Setter
@ToString
public class HUser {

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

    public static HUser createHUser (UserFormDto userFormDto, PasswordEncoder passwordEncoder){
        HUser hUser = new HUser();
        hUser.setId(userFormDto.getId());
        hUser.setPw(userFormDto.getPw());
        hUser.setEm(userFormDto.getEm());
        hUser.setNic(userFormDto.getNic());
        hUser.setPh(userFormDto.getPh());
        hUser.setAd(userFormDto.getAd());
        hUser.setRole(Role.USER);

        //SecurityConfig의 BCryptPasswordEncoder Bean을 파라미터로 넘겨서 비밀번호 암호화
        String Password = passwordEncoder.encode(userFormDto.getPw());
        hUser.setPw(Password);

        return hUser;
    }
}
