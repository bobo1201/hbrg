package com.hbrg.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "Hbrg_User")
@Getter
@Setter
@ToString
public class Hbrg_User {

    @Column(name="HbrgIdNum")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long HbrgIdNum; // 아이디 순서

    @Id
    @Column(name="HbrgId", nullable = false)
    private String HbrgId; // 로그인 ID

    @Column(name="HbrgPw", nullable = false)
    private String HbrgPw; // 로그인 PW

    @Column(name="HbrgEm", nullable = false)
    private String HbrgEm; // 회원가입 이메일

    @Column(name="HbrgNic", nullable = false)
    private String HbrgNic; // 회원가입 닉네임

    private Long HbrgPh; // 회원가입 전화번호

    private String HbrgAd; // 회원가입 주소
}
