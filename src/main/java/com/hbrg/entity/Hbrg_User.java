package com.hbrg.entity;

import com.hbrg.dto.Hbrg_UserDto;
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

    @Column(name="UserId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long UserId; // 아이디 순서

    @Id
    @Column(name="Id", nullable = false)
    private String id; // 로그인 ID

    @Column(name="Pw", nullable = false)
    private String pw; // 로그인 PW

    @Column(name="Em", nullable = false)
    private String em; // 회원가입 이메일

    @Column(name="Nic", nullable = false)
    private String nic; // 회원가입 닉네임

    @Column(name="Ph")
    private Long ph; // 회원가입 전화번호

    @Column(name="Ad")
    private String ad; // 회원가입 주소

    //Hbrg_User 엔티티 생성 메소드
    public static Hbrg_User createHbrg_User (Hbrg_UserDto hbrg_UserDto, PasswordEncoder passwordEncoder){
        Hbrg_User hbrg_user = new Hbrg_User();
        hbrg_UserDto.setId(hbrg_UserDto.getId());
        hbrg_UserDto.setPw(hbrg_UserDto.getPw());
        hbrg_UserDto.setEm(hbrg_UserDto.getEm());
        hbrg_UserDto.setNic(hbrg_UserDto.getNic());
        hbrg_UserDto.setPh(hbrg_UserDto.getPh());
        hbrg_UserDto.setAd(hbrg_UserDto.getAd());

        //SecurityConfig의 BCryptPasswordEncoder Bean을 파라미터로 넘겨서 비밀번호 암호화
        String Pw = passwordEncoder.encode(hbrg_UserDto.getPw());
        hbrg_user.setPw(Pw);

        return hbrg_user;
    }
}