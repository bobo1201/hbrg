package com.hbrg.entity;

import com.hbrg.dto.Hbrg_UserFormDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import javax.validation.Valid;

@Entity
@Table(name = "Hbrg_User")
@Getter
@Setter
@ToString
public class Hbrg_User {

//    @Column(name="UserId")
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long UserId; // 아이디 순서

    @Id
    @Column(name="Id", nullable = false)
    private String id; // 로그인 ID

    @Column(name="Pw", nullable = false)
    private String pw; // 로그인 PW

    @Column(name="Em", nullable = false, unique = true)
    private String em; // 회원가입 이메일

    @Column(name="Nic", nullable = false)
    private String nic; // 회원가입 닉네임

    @Column(name="Ph")
    private Long ph; // 회원가입 전화번호

    @Column(name="Ad")
    private String ad; // 회원가입 주소

    //Hbrg_User 엔티티 생성 메소드
    public static Hbrg_User createHbrg_User (@Valid Hbrg_UserFormDto hbrg_userFormDto, PasswordEncoder passwordEncoder){
        Hbrg_User hbrg_user = new Hbrg_User();

        hbrg_user.setId(hbrg_userFormDto.getId());
        hbrg_user.setPw(hbrg_userFormDto.getPw());
        hbrg_user.setEm(hbrg_userFormDto.getEm());
        hbrg_user.setNic(hbrg_userFormDto.getNic());
        hbrg_user.setPh(hbrg_userFormDto.getPh());
        hbrg_user.setAd(hbrg_userFormDto.getAd());

        //SecurityConfig의 BCryptPasswordEncoder Bean을 파라미터로 넘겨서 비밀번호 암호화
        String Pw = passwordEncoder.encode(hbrg_userFormDto.getPw());
        hbrg_user.setPw(Pw);

        return hbrg_user;
    }
}