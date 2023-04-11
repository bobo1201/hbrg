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

    @Column(name="UserId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long UserId; // 아이디 순서

    @Id
    @Column(name="Id", nullable = false)
    private String Id; // 로그인 ID

    @Column(name="Pw", nullable = false)
    private String Pw; // 로그인 PW

    @Column(name="Em", nullable = false)
    private String Em; // 회원가입 이메일

    @Column(name="Nic", nullable = false)
    private String Nic; // 회원가입 닉네임

    @Column(name="Ph")
    private Long Ph; // 회원가입 전화번호

    @Column(name="Ad")
    private String Ad; // 회원가입 주소
}
